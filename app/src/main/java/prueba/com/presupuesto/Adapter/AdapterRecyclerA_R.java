package prueba.com.presupuesto.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import prueba.com.presupuesto.Activity.MainActivity;
import prueba.com.presupuesto.Model.Categorias;
import prueba.com.presupuesto.Activity.GastosActivity;
import prueba.com.presupuesto.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by levaa on 6/18/2017.
 */

public class AdapterRecyclerA_R extends RecyclerView.Adapter< AdapterRecyclerA_R.ItemViewHolder> {

    public static final String PRESUPUESTO_TITLE = "title";
    public static final String PRESUPUESTO_IMAG = "imagen";
    public static final String PRESUPUESTO_VALUE1 = "valor_inicial";
    public static final String PRESUPUESTO_VALUE2 = "valor_disponible";


    private Context context;
    private String diponible;
    private List<Categorias> categorias;

    public AdapterRecyclerA_R(Context context, List<Categorias> categorias) {
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_info,parent,false);
        ItemViewHolder redditView = new ItemViewHolder(v);
        return redditView;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        holder.mTitle.setPaintFlags(holder.mTitle.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        holder.mTitle.setText(categorias.get(position).getTitles());
        holder.mValue1.setText("$"+categorias.get(position).getPresupuestoInicial());
        holder.mValue2.setText("$"+categorias.get(position).getMontoDisponoble());
        holder.mImagen.setImageResource(categorias.get(position).getImagen());



        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Posicion",String.valueOf(position));
                Intent intent = new Intent(context,GastosActivity.class);
                intent.putExtra("num_categoria",position);
                intent.putExtra(PRESUPUESTO_TITLE,categorias.get(position).getTitles());
                intent.putExtra(PRESUPUESTO_IMAG,categorias.get(position).getImagen());
                intent.putExtra(PRESUPUESTO_VALUE1,categorias.get(position).getPresupuestoInicial());
                intent.putExtra(PRESUPUESTO_VALUE2,categorias.get(position).getMontoDisponoble());
                context.startActivity(intent);


            }
        });

       /* if (diponible != holder.mValue2.getText().toString()){
            holder.mValue2.setText(diponible);
        }else {
            holder.mValue2.setText(categorias.get(position).getPresupuestoInicial());
        }*/

       /* switch (position){
            case 0:
                holder.mImagen.setImageResource(R.drawable.house);
                break;
            case 1:
                holder.mImagen.setImageResource(R.drawable.luz);
                break;
            case 2:
                holder.mImagen.setImageResource(R.drawable.alimentacion);
                break;
            case 3:
                holder.mImagen.setImageResource(R.drawable.diezmo);
                break;
            case 4:
                holder.mImagen.setImageResource(R.drawable.beach);
                break;
            case 5:
                holder.mImagen.setImageResource(R.drawable.transporte);
                break;
            case 6:
                holder.mImagen.setImageResource(R.drawable.diversion);
                break;
        }*/


    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }



    static class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImagen;
        private CardView mContainer;
        private TextView mTitle,mValue1,mValue2;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mContainer = (CardView)itemView.findViewById(R.id.card_view);
            mImagen = (ImageView) itemView.findViewById(R.id.image_item);
            mTitle = (TextView) itemView.findViewById(R.id.title_item);
            mValue1 = (TextView) itemView.findViewById(R.id.value1_item);
            mValue2 = (TextView) itemView.findViewById(R.id.value2_item);

        }
    }





}
