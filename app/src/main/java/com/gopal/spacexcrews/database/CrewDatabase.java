package com.gopal.spacexcrews.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gopal.spacexcrews.dao.CrewsDao;
import com.gopal.spacexcrews.modal.CrewsModal;

@Database(entities = {CrewsModal.class}, version = 1)
public abstract class CrewDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "CrewsDatabase";

    public abstract CrewsDao crewsDao();

    private static volatile CrewDatabase INSTANCE;

    public static CrewDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CrewDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder( context,
                            CrewDatabase.class,
                            DATABASE_NAME )
                            .addCallback( callback )
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate( db );
            new PopulateAsync( INSTANCE );
        }
    };

    static class PopulateAsync extends AsyncTask<Void, Void, Void> {

        private CrewsDao crewsDao;

        PopulateAsync(CrewDatabase crewDatabase) {
            crewsDao = crewDatabase.crewsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crewsDao.deleteAll();
            return null;
        }
    }
}
