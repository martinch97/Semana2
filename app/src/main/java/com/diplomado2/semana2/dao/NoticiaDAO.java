package com.diplomado2.semana2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diplomado2.semana2.db.DatabaseHelper;
import com.diplomado2.semana2.model.Noticia;

import java.util.ArrayList;
import java.util.List;

public class NoticiaDAO {

    private static String NOMBRE_TABLA = "noticias";

    private static String COLUMNA_ID = "id_noticia";
    private static String COLUMNA_TITULO = "titulo";
    private static String COLUMNA_IMAGEN = "imagen";

    public static String QUEY_CREAR_TABLA = " CREATE TABLE " + NOMBRE_TABLA + " (" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            COLUMNA_TITULO + " TEXT , " +
            COLUMNA_IMAGEN + " TEXT , " +
            " ) ";

    private DatabaseHelper databaseHelper;

    public NoticiaDAO(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void insertarNoticia(Noticia noticia) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUMNA_ID, noticia.getIdNoticia());
        valores.put(COLUMNA_TITULO, noticia.getTitulo());
        valores.put(COLUMNA_IMAGEN, noticia.getImagen());
        db.insert(NOMBRE_TABLA, null, valores);
    }

    public Noticia detalleNoticia(String idNoticia) {
        Noticia noticia = new Noticia();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        // SELECT * FROM 'noticias' WHERE 'id_noticia' = '{idNoticia}'
        Cursor cursor = db.query(NOMBRE_TABLA, null, COLUMNA_ID + " = ?", new String[]{idNoticia},
                null, null, null);
        if (cursor.moveToNext()) {
            // objeto noticia, con todos sus valores
            noticia.setIdNoticia(cursor.getString(0));
            noticia.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMNA_TITULO)));
            noticia.setImagen(cursor.getString(cursor.getColumnIndex(COLUMNA_IMAGEN)));
        }
        return noticia;
    }

    public List<Noticia> getNoticias() {
        List<Noticia> noticias = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        //select * from noticias
        Cursor cursor = db.query(NOMBRE_TABLA, null, null, null,
                null, null, null);
        while (cursor.moveToNext()) {
            // objeto noticia, con todos sus valores
            Noticia noticia = new Noticia();
            noticia.setIdNoticia(cursor.getString(0));
            noticia.setTitulo(cursor.getString(cursor.getColumnIndex(COLUMNA_TITULO)));
            noticia.setImagen(cursor.getString(cursor.getColumnIndex(COLUMNA_IMAGEN)));
            // llenamos la lista de 'noticias'
            noticias.add(noticia);
        }
        return noticias;
    }

    public void eliminarNoticia(String idNoticia) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // DELETE FROM 'noticias' WHERE 'id_noticia' = '{idNoticia}'
        db.delete(NOMBRE_TABLA, COLUMNA_ID + " = ?", new String[]{idNoticia});
    }

}
