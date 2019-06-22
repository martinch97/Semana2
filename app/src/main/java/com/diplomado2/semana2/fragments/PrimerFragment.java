package com.diplomado2.semana2.fragments;


import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplomado2.semana2.R;
import com.diplomado2.semana2.adapters.NoticiaAdapter;
import com.diplomado2.semana2.adapters.RvAdapter;
import com.diplomado2.semana2.dao.NoticiaDAO;
import com.diplomado2.semana2.databinding.FragmentPrimerBinding;
import com.diplomado2.semana2.db.Semana2RoomDatabase;
import com.diplomado2.semana2.model.Noticia;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrimerFragment extends Fragment {
    RecyclerView recyclerView;
    NoticiaDAO noticiaDAO;
    NoticiaAdapter noticiaAdapter;
    Semana2RoomDatabase db;
    FragmentPrimerBinding binding;

    public PrimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_primer, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        noticiaAdapter = new NoticiaAdapter(new ArrayList<Noticia>(), PrimerFragment.this);
        recyclerView.setAdapter(noticiaAdapter);
//        noticiaDAO = new NoticiaDAO(getContext());
        db = Semana2RoomDatabase.getDatabase(getActivity().getApplication());
        Noticia noticia = new Noticia();
        noticia.setIdNoticia("2");
        noticia.setImagen("https://s3-sa-east-1.amazonaws.com/assets.abc.com.py/2012/03/21/la-noticia-236438_595_366_1.jpg");
        noticia.setSync(true);
        noticia.setTitulo("noticia1");
        db.insertNoticia(noticia);
        db.noticiaDAORoom().getAllNoticias().observe(this, new Observer<List<Noticia>>() {
            @Override
            public void onChanged(@Nullable List<Noticia> noticias) {
                Log.d("TAG", "onChanged: " + noticias.size());
                noticiaAdapter.setNoticias(noticias);
            }
        });
//        Util.getService().getNoticias().enqueue(new Callback<List<Noticia>>() {
//            @Override
//            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
//                if (response.isSuccessful()) {
//                    for (Noticia noticia : response.body()) {
//                        noticiaDAO.insertarNoticia(noticia);
//                    }
//
//                    //hacer el listado de noticias
//                    noticiaAdapter = new NoticiaAdapter(response.body(), PrimerFragment.this);
//                    recyclerView.setAdapter(noticiaAdapter);
//
//                    //ejemplo de una lista, con listas horizontales embebidas
////                    setAdapterData(response.body());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Noticia>> call, Throwable t) {
//                Log.w("TAG", "onFailure: ", t);
//                noticiaAdapter = new NoticiaAdapter(noticiaDAO.getNoticias(), PrimerFragment.this);
//                recyclerView.setAdapter(noticiaAdapter);
////                setAdapterData(noticiaDAO.getNoticias());
//            }
//        });
    }

    private void setAdapterData(List<Noticia> noticias) {
        NoticiaAdapter noticiaAdapter = new NoticiaAdapter(noticias, PrimerFragment.this);
        recyclerView.setAdapter(noticiaAdapter);
        RvAdapter rvAdapter = new RvAdapter(PrimerFragment.this, noticiaAdapter);
        recyclerView.setAdapter(rvAdapter);
    }

//    private List<Noticia> getNoticias() {
//        List<Noticia> noticias = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Noticia noticia = new Noticia();
//            noticia.setId(String.valueOf(i));
//            noticia.setTitulo("Noticia " + i);
//            noticias.add(noticia);
//        }
//        return noticias;
//    }

}
