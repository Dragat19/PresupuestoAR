package prueba.com.presupuesto.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import prueba.com.presupuesto.Model.Gastos;
import prueba.com.presupuesto.R;


/**
 * Created by levaa on 6/23/2017.
 */

public class AdapterItemsCategoria extends RecyclerView.Adapter<AdapterItemsCategoria.ItemViewHolder> {

    public static final String NAME_PREF = "mi_presupuesto";
    public static final String PRESUPUESTO_INDEX = "index";
    public static final String DESCRIPCION = "Descripcion";
    public static final String GASTOS = "Valor_Gastos";
    private Context context;
    private List<Gastos> gastos;

    public AdapterItemsCategoria(Context context, List<Gastos> gastos) {
        this.context = context;
        this.gastos = gastos;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_categorias,parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.mDescripcion.setText(gastos.get(position).getDescripcion());
        holder.mGastos.setText("-$ "+gastos.get(position).getValorGastos());


    }

    @Override
    public int getItemCount() {
        return gastos.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView mDescripcion,mGastos;
        private CardView cardView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mDescripcion = (TextView) itemView.findViewById(R.id.descripcion_items);
            mGastos =  (TextView) itemView.findViewById(R.id.gastos_items);
            cardView = (CardView) itemView.findViewById(R.id.card_view_categorias);
        }
    }

    public void GuardarPreferencia(int index, TextView Decripcion, TextView valorGastos)
    {
        SharedPreferences mispreferencias= context.getSharedPreferences(NAME_PREF, context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mispreferencias.edit();
        String strDecripcion= Decripcion.getText().toString();
        String strGastos= valorGastos.getText().toString();

        editor.putInt(PRESUPUESTO_INDEX,index);
        editor.putString(DESCRIPCION, strDecripcion);
        editor.putString(GASTOS, strGastos);
        editor.commit();
    }
}
