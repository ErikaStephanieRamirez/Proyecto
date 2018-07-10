package com.ramirez.proyecto.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ramirez.proyecto.Categoria;
import com.ramirez.proyecto.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public abstract class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriasViewHolder> {
    private List<Categoria> cats;
    public Context ctx;
    public Context context;






    public static class CategoriasViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView title,description;
        ImageView img;
        Button btn;
        Context ctx;



        public CategoriasViewHolder(View itemView){
            super(itemView);
            card=itemView.findViewById(R.id.card_viewcat);
            title=itemView.findViewById(R.id.titulo);
            description=itemView.findViewById(R.id.desc);
            btn = itemView.findViewById(R.id.ver);
            ctx= itemView.getContext();



        }

    }
    public CategoriaAdapter(List<Categoria> news, Context context) {
        this.cats = news;
        this.ctx = context;

    }



    @Override
    public CategoriasViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_categoria,parent,false);

        return (new CategoriasViewHolder(v));
    }

    @Override
    public void onBindViewHolder(CategoriasViewHolder holder, final int position) {

        final Categoria categoria = cats.get(position);
        holder.title.setText(categoria.getNombre());
        holder.description.setText(categoria.getDescripcion());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categname = categoria.getNombre();
                CategoriaSeleccionada(categname);
            }
        });
    }
    public abstract void CategoriaSeleccionada(String ctn);


    @Override
    public int getItemCount(){
        return cats.size();
    }
}
