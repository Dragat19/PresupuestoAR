package prueba.com.presupuesto.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import prueba.com.presupuesto.Adapter.AdapterItemsCategoria;
import prueba.com.presupuesto.DialogoPresupuesto;
import prueba.com.presupuesto.Model.Gastos;
import prueba.com.presupuesto.R;

import static prueba.com.presupuesto.Adapter.AdapterRecyclerA_R.PRESUPUESTO_IMAG;
import static prueba.com.presupuesto.Adapter.AdapterRecyclerA_R.PRESUPUESTO_TITLE;
import static prueba.com.presupuesto.Adapter.AdapterRecyclerA_R.PRESUPUESTO_VALUE1;
import static prueba.com.presupuesto.Adapter.AdapterRecyclerA_R.PRESUPUESTO_VALUE2;

/**
 * Created by levaa on 6/22/2017.
 */

public class GastosActivity extends AppCompatActivity {
    private List<Gastos> gastos_items;
    private RecyclerView recyclerView;
    private TextView txPresupuesto,txDisponible,txGastos,txTitle;
    private ImageView img;
    private AdapterItemsCategoria adapter;
    private FloatingActionButton floatButonn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        floatButonn = (FloatingActionButton) findViewById(R.id.fabutton2);
        txDisponible = (TextView)findViewById(R.id.tx_disp);
        txPresupuesto = (TextView)findViewById(R.id.tx_presup);
        txGastos = (TextView)findViewById(R.id.tx_gasto);
        txTitle = (TextView) findViewById(R.id.title_categoria);
        img = (ImageView) findViewById(R.id.imag_categorias);
        recyclerView = (RecyclerView)findViewById(R.id.recycler2);
        gastos_items = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdapterItemsCategoria(this,gastos_items);
        recyclerView.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int intImagen= bundle.getInt(PRESUPUESTO_IMAG);
            int intPosicion= bundle.getInt("num_categoria");
            String strTitle= bundle.getString(PRESUPUESTO_TITLE);
            String strPresupuestoInicial = bundle.getString(PRESUPUESTO_VALUE1);
            String strDisponible = bundle.getString(PRESUPUESTO_VALUE2);

            txPresupuesto.setText(strPresupuestoInicial);
            img.setImageResource(intImagen);
            txTitle.setText(strTitle);
            txDisponible.setText(strDisponible);

            Log.e("Posicion layout gastos",String.valueOf(intPosicion));
        }


        floatButonn.setOnClickListener(onAddingListener());

    }

    private View.OnClickListener onAddingListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(GastosActivity.this);
                dialog.setContentView(R.layout.dialog_add);
                dialog.setCancelable(false);
                final EditText etDescripciones = (EditText) dialog.findViewById(R.id.et_descripcion);
                final EditText etGastos = (EditText) dialog.findViewById(R.id.et_gastos);
                View btnOk = dialog.findViewById(R.id.btn_ok);
                View btnCancel = dialog.findViewById(R.id.btn_cancel);
                btnOk.setOnClickListener(onConfirmationListener(etDescripciones,etGastos,dialog));
                btnCancel.setOnClickListener(onCancelListener(dialog));
                dialog.show();

               /* DialogoPresupuesto dialogoPresupuesto =new DialogoPresupuesto(GastosActivity.this);
                dialogoPresupuesto.show();*/
            }
        };
    }

    private View.OnClickListener onConfirmationListener(final EditText Descripcion, final EditText Monto,final Dialog dialog){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringDes = Descripcion.getText().toString().trim();
                String srtringGas =  Monto.getText().toString().trim();
                if (!srtringGas.equals("") && !stringDes.equals("") ){
                    Gastos gastos = new Gastos(stringDes,srtringGas);
                    gastos_items.add(gastos);
                    sumaGastos(Monto);
                    restaGastos();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(GastosActivity.this,"Faltan Datos",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private View.OnClickListener onCancelListener (final Dialog dialog){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
    }

    private void restaGastos(){
        String total = txPresupuesto.getText().toString();
        String gast = txGastos.getText().toString();
        int resta = Integer.valueOf(total) - Integer.valueOf(gast);
        if (resta <= 0 ){
            txDisponible.setText("0");
        }else {
            txDisponible.setText(String.valueOf(resta));
        }

    }

    private void sumaGastos(EditText monto){
            String stringMonto = monto.getText().toString();
            String GastosAnteriores = txGastos.getText().toString();
            int GastosTotales = Integer.valueOf(GastosAnteriores) + Integer.valueOf(stringMonto);
            txGastos.setText(String.valueOf(GastosTotales));
    }



}
