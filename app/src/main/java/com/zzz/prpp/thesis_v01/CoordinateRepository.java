package com.zzz.prpp.thesis_v01;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CoordinateRepository {

    private CoordinateDao mCoordinateDao;
    private LiveData<List<Coordinate>> mAllCoordinates;
    CoordinateRepository(Application application) {
        NodeRoomDatabase db = NodeRoomDatabase.getDatabase(application);
        mCoordinateDao = db.coordinateDao();
        mAllCoordinates = mCoordinateDao.getAllCoordinates();
    }

    LiveData<List<Coordinate>> getmAllCoordinates(){
        return mAllCoordinates;
    }

    public  void insert (Coordinate coordinate) { new insertAsyncTask(mCoordinateDao).execute(coordinate);}

    private  static class  insertAsyncTask extends AsyncTask<Coordinate, Void, Void> {

        private CoordinateDao mAsyncTaskDao;

        insertAsyncTask(CoordinateDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Coordinate... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
