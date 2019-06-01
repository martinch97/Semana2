package com.diplomado2.semana2.api;

import com.diplomado2.semana2.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/noticias.php")
    Call<List<Noticia>> getNoticias();
}
