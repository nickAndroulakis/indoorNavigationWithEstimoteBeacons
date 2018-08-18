package com.zzz.prpp.thesis_v01;

import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity(tableName="Node_table")
public class Node {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @NonNull
    private Integer mNumber;
    private Calibration mCal;

    //constructor
    public Node(@NonNull Integer number, Calibration cal) {
        this.mNumber = number;
        this.mCal = cal;
    }

    //getters
    public Integer getmId() {return this.mId;}

    @NonNull
    public Integer getmNumber() {return this.mNumber;}
    public Calibration getmCal() {return this.mCal;}

}
