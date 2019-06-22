package com.diplomado2.semana2.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import com.diplomado2.semana2.dao.NoticiaDAORoom;
import com.diplomado2.semana2.model.Noticia;

@Database(entities = {Noticia.class}, version = 1)
public abstract class Semana2RoomDatabase extends RoomDatabase {
    private static volatile Semana2RoomDatabase INSTANCE;

    public static Semana2RoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Semana2RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Semana2RoomDatabase.class, "semana2_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract NoticiaDAORoom noticiaDAORoom();

    public void insertNoticia(Noticia noticia) {
        new insertAsyncTask(noticiaDAORoom()).execute(noticia);
    }

    private static class insertAsyncTask extends AsyncTask<Noticia, Void, Void> {

        private NoticiaDAORoom mAsyncTaskDao;

        insertAsyncTask(NoticiaDAORoom dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Noticia... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}