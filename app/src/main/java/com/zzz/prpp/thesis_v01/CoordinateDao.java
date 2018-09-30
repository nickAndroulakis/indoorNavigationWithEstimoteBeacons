package com.zzz.prpp.thesis_v01;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CoordinateDao {

    @Insert
    void insert(Coordinate coordinate);

    @Query("DELETE FROM coordinate_table")
    void deleteAll();

    @Query("SELECT * from coordinate_table ORDER BY mId ASC")
    LiveData<List<Coordinate>> getAllCoordinates();
}
