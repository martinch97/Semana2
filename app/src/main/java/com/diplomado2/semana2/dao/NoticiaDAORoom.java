package com.diplomado2.semana2.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.diplomado2.semana2.model.Noticia;

import java.util.List;

@Dao
public interface NoticiaDAORoom {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Noticia noticia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Noticia> noticias);

    @Query("DELETE FROM noticiaTable")
    void deleteAll();

    @Query("SELECT * from noticiaTable ORDER BY _id ASC")
    LiveData<List<Noticia>> getAllNoticias();
}