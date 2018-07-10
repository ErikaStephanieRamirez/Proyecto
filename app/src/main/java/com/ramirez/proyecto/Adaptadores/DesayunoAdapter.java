package com.ramirez.proyecto.Adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramirez.proyecto.R;
import com.ramirez.proyecto.RoomArchitecture.Entities.DesayunoEntity;
import com.ramirez.proyecto.RoomArchitecture.Entities.PolloEntity;
import com.ramirez.proyecto.RoomArchitecture.ViewModel.DesayunoViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DesayunoAdapter extends RecyclerView.Adapter<DesayunoAdapter.DesayunoViewHolder> {
    private List<DesayunoEntity> bebidas;
    public Context context;


    @Override
    public DesayunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_bebidas,parent,false);

        return (new DesayunoViewHolder(v));
    }

    @Override
    public void onBindViewHolder(DesayunoViewHolder holder, int position) {
        final DesayunoEntity bebida = bebidas.get(position);
        holder.nombreBebida.setText(bebida.getName());
        holder.precioBebida.setText(String.valueOf(bebida.getPrice()));
        //Comprobamos si la posicion de la imagen es diferente de nula
        if (!(bebida.getProductImage() == null)) {
            //Utilizamos la libreria Picasso para manejar las imagenes y el almacenamiento en cache
            Picasso.with(context).load(bebida
                    .getProductImage())
                    .error(R.drawable.pupusas).into(holder.imagen);
        } else {
            //Imagen por default en caso de que la noticia no tenga imagen alguna
            Picasso.with(context).load(R.drawable.pupusas).error(R.drawable.pupusas).into(holder.imagen);
        }

    }

    @Override
    public int getItemCount() {
        return bebidas.size();
    }

    public DesayunoAdapter(List<DesayunoEntity> bebidas, Context context) {
        this.bebidas = bebidas;
        this.context = context;
    }

    public static class DesayunoViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView nombreBebida,precioBebida;
        ImageView imagen;
        Button btn;
        Context context;



        public DesayunoViewHolder(View itemView){
            super(itemView);
            cardview = itemView.findViewById(R.id.card_bebidas);
            nombreBebida = itemView.findViewById(R.id.txtview_bebida);
            precioBebida = itemView.findViewById(R.id.txtview_precio);
            imagen = itemView.findViewById(R.id.imagen_bebida);
            context = itemView.getContext();

        }

    }
}
