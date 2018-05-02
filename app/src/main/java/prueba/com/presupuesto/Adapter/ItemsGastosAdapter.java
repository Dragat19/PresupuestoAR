package prueba.com.presupuesto.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import prueba.com.presupuesto.Model.Gastos;
import prueba.com.presupuesto.R;


/**
 * Created by levaa on 6/23/2017.
 */

public class ItemsGastosAdapter extends RecyclerView.Adapter<ItemsGastosAdapter.ItemViewHolder> {

    private Context context;
    private List<Gastos> gastos;

    public ItemsGastosAdapter(Context context, List<Gastos> gastos) {
        this.context = context;
        this.gastos = gastos;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_categorias, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.mDescripcion.setText(gastos.get(position).getDescripcion());
        holder.mGastos.setText("-$ " + gastos.get(position).getValorGastos());
    }

    @Override
    public int getItemCount() {
        return gastos.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mDescripcion, mGastos;

        ItemViewHolder(View itemView) {
            super(itemView);
            mDescripcion = (TextView) itemView.findViewById(R.id.descripcion_items);
            mGastos = (TextView) itemView.findViewById(R.id.gastos_items);
        }
    }
}
