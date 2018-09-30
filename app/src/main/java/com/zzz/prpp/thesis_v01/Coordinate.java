package com.zzz.prpp.thesis_v01;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName="Coordinate_table")
public class Coordinate {

    @PrimaryKey
    private Integer mId;
    private Integer mX;
    private Integer mY;

    //Constructors
    public Coordinate(Integer id, Integer x, Integer y){
        this.mId = id;
        this.mX = x;
        this.mY = y;
    }


    //getters
    public Integer getMId() {return mId;}
    public Integer getMX() {return mX;}
    public Integer getMY() {return mY;}

    //setters
    public void setMId(Integer mId) {this.mId = mId;}
    public void setMX(Integer mX) {this.mX = mX;}
    public void setMY(Integer mY) {this.mY = mY;}
}
