package com.diplomado2.semana2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplomado2.semana2.R;
import com.diplomado2.semana2.fragments.PrimerFragment;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    PrimerFragment fragment;
    NoticiaAdapter noticiaAdapter;

    public RvAdapter(PrimerFragment fragment, NoticiaAdapter noticiaAdapter) {
        this.fragment = fragment;
        this.noticiaAdapter = noticiaAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_rv, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvHeader.setText("Section " + i);
        viewHolder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewHolder.recyclerView.setLayoutManager(linearLayoutManager);
        viewHolder.recyclerView.setAdapter(noticiaAdapter);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }
}
