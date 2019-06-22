package com.diplomado2.semana2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "noticiaTable")
public class Noticia {
    //    @PrimaryKey(autoGenerate = true)
    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("id_noticia")
    @NonNull
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
