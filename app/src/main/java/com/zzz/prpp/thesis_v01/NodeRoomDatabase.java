package com.zzz.prpp.thesis_v01;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Node.class}, version = 1, exportSchema = false)
public abstract class NodeRoomDatabase extends RoomDatabase {

    public abstract NodeDao nodeDao();

    private static NodeRoomDatabase INSTANCE;


    static NodeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NodeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NodeRoomDatabase.class, "node_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }


    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NodeDao mDao;

        PopulateDbAsync(NodeRoomDatabase db) {
            mDao = db.nodeDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            //mDao.deleteAll();
            Node node = new Node(1,1.0,1.0,10.0,10.0,10.0,10.0);
            mDao.insert(node);
            return null;
        }
    }
}