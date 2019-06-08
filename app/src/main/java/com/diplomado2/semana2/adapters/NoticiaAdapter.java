package com.diplomado2.semana2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.diplomado2.semana2.R;
import com.diplomado2.semana2.fragments.PrimerFragment;
import com.diplomado2.semana2.model.Noticia;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {
    List<Noticia> noticias;
    PrimerFragment fragment;

    public NoticiaAdapter(List<Noticia> noticias, PrimerFragment fragment) {
        this.noticias = noticias;
        this.fragment = fragment;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Noticia noticia = noticias.get(i);
        viewHolder.titulo.setText(noticia.getTitulo());
        Picasso.get().load(noticia.getImagen()).into(viewHolder.imagen);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_noticia, viewGroup, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            imagen = itemView.findViewById(R.id.imagen);
        }
    }
}
