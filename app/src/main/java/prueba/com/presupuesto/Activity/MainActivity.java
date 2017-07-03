package prueba.com.presupuesto.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import prueba.com.presupuesto.Adapter.AdapterRecyclerA_R;
import prueba.com.presupuesto.DB_Presupuestos;
import prueba.com.presupuesto.Model.Categorias;
import prueba.com.presupuesto.R;

import static prueba.com.presupuesto.DB_Presupuestos.DATABASE_NAME;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private AdapterRecyclerA_R adapter;
    private List<Categorias> categorias;
    private Spinner spAno,spMes,spMoneda;
    private String date;
    private String[] title = {"Residencias","Luz","Comida","Diezmo","Vacaciones","Transporte","Diversion"};
    private String[] Presupuesto = {"8000","600","4000","770","2000","800","2000"};
    private String[] Disponible = {"8000","600","4000","770","2000","800","2000"};
    private int[] img = {R.drawable.house,R.drawable.luz,R.drawable.alimentacion,R.drawable.diezmo,R.drawable.beach,R.drawable.transporte,R.drawable.diversion};
    private DB_Presupuestos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_presupuesto);
        spAno = (Spinner)findViewById(R.id.ano_spinner);
        spMes = (Spinner)findViewById(R.id.mes_spinner);

        categorias = new ArrayList<>();
        date = new SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(new Date());
        Log.e("Fecha",date);

        db = new DB_Presupuestos(MainActivity.this,DATABASE_NAME,null,1);
        String mes = spMes.getSelectedItem().toString();
        String ano = spAno.getSelectedItem().toString();
        String fechaSelecionada = mes+ " " + ano;

        if (db.getListCategorias().size() != 0){
            Log.e("Fecha Seleccionada" , fechaSelecionada);

            adapter = new AdapterRecyclerA_R(this,db.getListCategorias());

        }else {
            ListaCategoria();
            adapter = new AdapterRecyclerA_R(this,categorias);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //ListaCategoria();


    }

    private void ListaCategoria(){
        categorias.add(new Categorias(title[0],Presupuesto[0],Disponible[0],img[0]));
        categorias.add(new Categorias(title[1],Presupuesto[1],Disponible[1],img[1]));
        categorias.add(new Categorias(title[2],Presupuesto[2],Disponible[2],img[2]));
        categorias.add(new Categorias(title[3],Presupuesto[3],Disponible[3],img[3]));
        categorias.add(new Categorias(title[4],Presupuesto[4],Disponible[4],img[4]));
        categorias.add(new Categorias(title[5],Presupuesto[5],Disponible[5],img[5]));
        categorias.add(new Categorias(title[6],Presupuesto[6],Disponible[6],img[6]));

        if (categorias.size() != 0){
            for (int i = 0; i < 7 ; i++) {
                db.addNewCategoria(categorias.get(i),date);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
