package com.zzz.prpp.thesis_v01;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NodeRepository {

    private NodeDao mNodeDao;
    private LiveData<List<Node>> mAllNodes;
    NodeRepository(Application application) {
        NodeRoomDatabase db = NodeRoomDatabase.getDatabase(application);
        mNodeDao = db.nodeDao();
        mAllNodes = mNodeDao.getAllNodes();
    }

    LiveData<List<Node>> getAllNodes() {
        return mAllNodes;
    }


    public void insert (Node node) {
        new insertAsyncTask(mNodeDao).execute(node);
    }

    private static class insertAsyncTask extends AsyncTask<Node, Void, Void> {

        private NodeDao mAsyncTaskDao;

        insertAsyncTask(NodeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Node... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}