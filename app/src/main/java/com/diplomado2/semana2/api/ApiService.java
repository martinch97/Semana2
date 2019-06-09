package com.diplomado2.semana2.api;

import com.diplomado2.semana2.api.request.RequestLogin;
import com.diplomado2.semana2.api.request.RequestRegistro;
import com.diplomado2.semana2.api.response.ResponseLogin;
import com.diplomado2.semana2.api.response.ResponseRegistro;
import com.diplomado2.semana2.model.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("api/noticias.php")
    Call<List<Noticia>> getNoticias();

    @POST("api/login.php")
    Call<ResponseLogin> postLogin(@Body RequestLogin requestLogin);

    @POST("api/registro.php")
    Call<ResponseRegistro> postRegistro(@Body RequestRegistro requestRegistro);

}
