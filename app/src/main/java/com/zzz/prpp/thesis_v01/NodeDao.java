package com.zzz.prpp.thesis_v01;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NodeDao {

    @Insert
    void insert(Node node);

    @Query("DELETE FROM node_table")
    void deleteAll();

    @Query("SELECT * from node_table ORDER BY mId ASC")
    List<Node> getAllNodes();

}
