package prueba.com.presupuesto.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prueba.com.presupuesto.Model.Gastos;
import prueba.com.presupuesto.R;
import prueba.com.presupuesto.database.DBGastos;

/**
 * Created by albertsanchez on 27/8/17.
 */

public class GastosDialog extends Dialog {

    private Button btnOk,btnCancel;
    private EditText etDescripcion,etAddGasto;
    private List<Gastos> gastos_items;
    private String title,gasto;
    private Context context;
    private DBGastos db;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String monto);
    }


    public GastosDialog(Context context,String title,String gasto,List<Gastos> gastos_items,OnItemClickListener listener) {
        super(context);
        this.context = context;
        this.title = title;
        this.gasto = gasto;
        this.gastos_items = gastos_items;
        this.listener = listener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setCancelable(false);

//        Cast View
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        etDescripcion = (EditText) findViewById(R.id.et_descripcion);
        etAddGasto = (EditText) findViewById(R.id.et_gastos);

        db = new DBGastos(context, DBGastos.DATABASE_NAME2, null, 1);
        gastos_items = new ArrayList<>();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strDescripcion = etDescripcion.getText().toString();
                String strAddGastos = etAddGasto.getText().toString();

                if (!strAddGastos.isEmpty() && !strDescripcion.isEmpty()) {
                    Gastos gastos = new Gastos(title, strDescripcion, strAddGastos);
                    gastos_items.add(gastos);
                    db.addNewItemsGastos(gastos, "07/08/2017");
                    Double GastosTotales = Double.valueOf(gasto) + Double.valueOf(strAddGastos);
                    listener.onItemClick(String.valueOf(GastosTotales));
                    dismiss();
                    //sumaGastos(Monto);
                    //restaGastos();
                    //adapter.notifyDataSetChanged();
                    //dialog.dismiss();
                } else {
                    Toast.makeText(context, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
