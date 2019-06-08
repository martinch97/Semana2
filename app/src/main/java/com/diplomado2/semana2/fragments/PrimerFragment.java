package com.diplomado2.semana2.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplomado2.semana2.R;
import com.diplomado2.semana2.adapters.NoticiaAdapter;
import com.diplomado2.semana2.adapters.RvAdapter;
import com.diplomado2.semana2.api.ApiService;
import com.diplomado2.semana2.model.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrimerFragment extends Fragment {
    RecyclerView recyclerView;

    public PrimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_primer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://androidbasico-martincs27.c9users.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        service.getNoticias().enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful()) {
                    //hacer el listado de noticias
//                    NoticiaAdapter noticiaAdapter = new NoticiaAdapter(response.body(), PrimerFragment.this);
//                    recyclerView.setAdapter(noticiaAdapter);

                    //ejemplo de una lista, con listas horizontales embebidas
                    NoticiaAdapter noticiaAdapter = new NoticiaAdapter(response.body(), PrimerFragment.this);
                    recyclerView.setAdapter(noticiaAdapter);
                    RvAdapter rvAdapter = new RvAdapter(PrimerFragment.this, noticiaAdapter);
                    recyclerView.setAdapter(rvAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {

            }
        });
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
