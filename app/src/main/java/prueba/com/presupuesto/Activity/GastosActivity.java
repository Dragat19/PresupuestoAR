package prueba.com.presupuesto.Activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prueba.com.presupuesto.Adapter.ItemsGastosAdapter;
import prueba.com.presupuesto.Model.Gastos;
import prueba.com.presupuesto.R;
import prueba.com.presupuesto.database.DBGastos;
import static prueba.com.presupuesto.Adapter.ItemsCategoriasAdapter.PRESUPUESTO_IMAG;
import static prueba.com.presupuesto.Adapter.ItemsCategoriasAdapter.PRESUPUESTO_POSICION;
import static prueba.com.presupuesto.Adapter.ItemsCategoriasAdapter.PRESUPUESTO_TITLE;
import static prueba.com.presupuesto.Adapter.ItemsCategoriasAdapter.PRESUPUESTO_VALUE1;
import static prueba.com.presupuesto.Adapter.ItemsCategoriasAdapter.PRESUPUESTO_VALUE2;

/**
 * Created by levaa on 6/22/2017.
 */

public class GastosActivity extends AppCompatActivity {
    private static final String TAG = "GastosActivity";
    private List<Gastos> gastos_items;
    private RecyclerView recyclerView;
    private TextView txPresupuesto, txDisponible, txGastos, txTitle;
    private ImageView img;
    private ItemsGastosAdapter adapter;
    private DBGastos db;
    private FloatingActionButton floatButonn;
    private String strTitle, strPresupuestoInicial, strDisponible;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txDisponible = (TextView) findViewById(R.id.tx_disp);
        txPresupuesto = (TextView) findViewById(R.id.tx_presup);
        txGastos = (TextView) findViewById(R.id.tx_gasto);
        txTitle = (TextView) findViewById(R.id.title_categoria);
        floatButonn = (FloatingActionButton) findViewById(R.id.fabutton2);
        img = (ImageView) findViewById(R.id.imag_categorias);
        recyclerView = (RecyclerView) findViewById(R.id.recycler2);
        db = new DBGastos(this, DBGastos.DATABASE_NAME2, null, 1);

        gastos_items = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new ItemsGastosAdapter(this, gastos_items);
        recyclerView.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int intImagen = bundle.getInt(PRESUPUESTO_IMAG);
            int intPosicion = bundle.getInt(PRESUPUESTO_POSICION);
            strTitle = bundle.getString(PRESUPUESTO_TITLE);
            strPresupuestoInicial = bundle.getString(PRESUPUESTO_VALUE1);
            strDisponible = bundle.getString(PRESUPUESTO_VALUE2);

            txPresupuesto.setText(strPresupuestoInicial);
            img.setImageResource(intImagen);
            txTitle.setText(strTitle);
            txDisponible.setText(strDisponible);

            Log.d(TAG, "Posicion " + String.valueOf(intPosicion));
        }

        floatButonn.setOnClickListener(onAddingListener());

    }

    private View.OnClickListener onAddingListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(GastosActivity.this);
                dialog.setContentView(R.layout.dialog_add);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(false);
                final EditText etDescripciones = (EditText) dialog.findViewById(R.id.et_descripcion);
                final EditText etGastos = (EditText) dialog.findViewById(R.id.et_gastos);
                View btnOk = dialog.findViewById(R.id.btn_ok);
                View btnCancel = dialog.findViewById(R.id.btn_cancel);
                btnOk.setOnClickListener(onConfirmationListener(etDescripciones, etGastos, dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));
                dialog.show();
            }
        };
    }

    private View.OnClickListener onConfirmationListener(final EditText Descripcion, final EditText Monto, final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringDes = Descripcion.getText().toString().trim();
                String srtringGas = Monto.getText().toString().trim();
                if (!srtringGas.isEmpty() && !stringDes.isEmpty()) {
                    Gastos gastos = new Gastos(strTitle, stringDes, srtringGas);
                    gastos_items.add(gastos);
                    db.addNewItemsGastos(gastos,"07/08/2017");
                    sumaGastos(Monto);
                    restaGastos();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(GastosActivity.this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private View.OnClickListener onCancelListener(final Dialog dialog) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }

    private void restaGastos() {
        String total = txPresupuesto.getText().toString();
        String gast = txGastos.getText().toString();
        Double resta = Double.valueOf(total) - Double.valueOf(gast);
        if (resta <= 0) {
            txDisponible.setText("0");
        } else {
            txDisponible.setText(String.valueOf(resta));
        }
    }

    private void sumaGastos(EditText monto) {
        String stringMonto = monto.getText().toString();
        String GastosAnteriores = txGastos.getText().toString();
        Double GastosTotales = Double.valueOf(GastosAnteriores) + Double.valueOf(stringMonto);
        txGastos.setText(String.valueOf(GastosTotales));
    }


}
