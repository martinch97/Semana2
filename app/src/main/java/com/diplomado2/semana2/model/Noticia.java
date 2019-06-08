package com.diplomado2.semana2.model;

import com.google.gson.annotations.SerializedName;

public class Noticia {
    @SerializedName("id_noticia")
    private String idNoticia;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("imagen")
    private String imagen;
    private boolean sync;

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
