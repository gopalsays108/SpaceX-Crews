package com.gopal.spacexcrews.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.gopal.spacexcrews.dao.CrewsDao;
import com.gopal.spacexcrews.database.CrewDatabase;
import com.gopal.spacexcrews.modal.CrewsModal;

import java.util.List;

public class CrewsRepository {
    private CrewDatabase crewDatabase;

    private LiveData<List<CrewsModal>> getAllCrews;

    public CrewsRepository(Application application) {
        crewDatabase = CrewDatabase.getInstance( application );
        getAllCrews = crewDatabase.crewsDao().getAllCrews();
    }

    public LiveData<List<CrewsModal>> getCrews() {
        return getAllCrews;
    }

    public void deleteALl() {
        new DeleteAll( crewDatabase ).execute();
    }

    public void insertCrew(List<CrewsModal> crewsModals) {
        new InsertAsyncTask( crewDatabase ).execute( crewsModals );
    }

    public void deleteUserById(String id) {
        new DeleteById( crewDatabase ).execute( id );
    }


    static class DeleteById extends AsyncTask<String, Void, Void> {

        private CrewsDao crewsDao;

        DeleteById(CrewDatabase crewDatabase) {
            crewsDao = crewDatabase.crewsDao();
        }

        @Override
        protected Void doInBackground(String... id) {
            crewsDao.deleteByUserId( id[0] );
            return null;
        }
    }

    static class DeleteAll extends AsyncTask<Void, Void, Void> {

        private CrewsDao crewsDao;

        DeleteAll(CrewDatabase crewDatabase) {
            crewsDao = crewDatabase.crewsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crewsDao.deleteAll();
            return null;
        }
    }

    static class InsertAsyncTask extends AsyncTask<List<CrewsModal>, Void, Void> {

        private CrewsDao crewsDao;

        InsertAsyncTask(CrewDatabase crewDatabase) {
            crewsDao = crewDatabase.crewsDao();
        }

        @Override
        protected Void doInBackground(List<CrewsModal>... lists) {
            Log.d( "TAG", "insertCrew: bg " + lists[0] );
            crewsDao.insertCrews( lists[0] );
            return null;
        }
    }
}
