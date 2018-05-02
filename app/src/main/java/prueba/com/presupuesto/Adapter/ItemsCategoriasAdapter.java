package prueba.com.presupuesto.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import prueba.com.presupuesto.Activity.GastosActivity;
import prueba.com.presupuesto.Model.Categorias;
import prueba.com.presupuesto.R;

/**
 * Created by levaa on 6/18/2017.
 */

public class ItemsCategoriasAdapter extends RecyclerView.Adapter<ItemsCategoriasAdapter.ItemViewHolder> {

    public static final String PRESUPUESTO_TITLE = "title";
    public static final String PRESUPUESTO_IMAG = "imagen";
    public static final String PRESUPUESTO_VALUE1 = "valor_inicial";
    public static final String PRESUPUESTO_VALUE2 = "valor_disponible";
    public static final String PRESUPUESTO_POSICION = "num_categoria";

    private Context context;
    private List<Categorias> categorias;

    public ItemsCategoriasAdapter(Context context, List<Categorias> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_info, parent, false);
        ItemViewHolder ItemViewHolder = new ItemViewHolder(v);
        return ItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.mTitle.setPaintFlags(holder.mTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.mTitle.setText(categorias.get(position).getTitles());
        holder.mValue1.setText("$" + categorias.get(position).getPresupuestoInicial());
        holder.mValue2.setText("$" + categorias.get(position).getMontoDisponoble());
        holder.mImagen.setImageResource(categorias.get(position).getImagen());

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GastosActivity.class);
                intent.putExtra(PRESUPUESTO_TITLE, categorias.get(position).getTitles());
                intent.putExtra(PRESUPUESTO_IMAG, categorias.get(position).getImagen());
                intent.putExtra(PRESUPUESTO_VALUE1, categorias.get(position).getPresupuestoInicial());
                intent.putExtra(PRESUPUESTO_VALUE2, categorias.get(position).getMontoDisponoble());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorias != null ? categorias.size() : 0;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImagen;
        private CardView mContainer;
        private TextView mTitle, mValue1, mValue2;

        ItemViewHolder(View itemView) {
            super(itemView);

            mContainer = (CardView) itemView.findViewById(R.id.card_view);
            mImagen = (ImageView) itemView.findViewById(R.id.image_item);
            mTitle = (TextView) itemView.findViewById(R.id.title_item);
            mValue1 = (TextView) itemView.findViewById(R.id.value1_item);
            mValue2 = (TextView) itemView.findViewById(R.id.value2_item);

        }
    }


}
