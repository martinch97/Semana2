package com.diplomado2.semana2;

import com.diplomado2.semana2.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {
    public static ApiService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://androidbasico-martincs27.c9users.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
