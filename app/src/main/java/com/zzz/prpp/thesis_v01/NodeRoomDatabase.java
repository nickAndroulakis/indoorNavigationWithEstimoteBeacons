package com.zzz.prpp.thesis_v01;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Node.class}, version = 1)
public abstract class NodeRoomDatabase extends RoomDatabase {

    public abstract NodeDao nodeDao();

    private static NodeRoomDatabase INSTANCE;


    static NodeRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NodeRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NodeRoomDatabase.class, "node_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}