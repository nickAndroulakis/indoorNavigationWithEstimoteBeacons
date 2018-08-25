package com.zzz.prpp.thesis_v01;

import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity(tableName="Node_table")
public class Node {

    @PrimaryKey(autoGenerate = true)
    private Integer mId;
    private Double mOne;
    private Double mTwo;
    private Double mThree;
    private Double mFour;
    private Double mFive;
    private Double mSix;

    @NonNull
    public Integer mNumber;

    //constructor
    public Node(@NonNull Integer number, Double one, Double two, Double three, Double four, Double five, Double six) {
        this.mNumber = number;
        this.mOne = one;
        this.mTwo = two;
        this.mThree = three;
        this.mFour = four;
        this.mFive = five;
        this.mSix = six;
    }

    //getters
    public Integer getmId() {return this.mId;}
    @NonNull
    public Integer getmNumber() {return this.mNumber;}
    public Double getmOne() {return this.mOne;}
    public Double getmTwo() {return this.mTwo;}
    public Double getmThree() {return this.mThree;}
    public Double getmFour() {return this.mFour;}
    public Double getmFive() {return this.mFive;}
    public Double getmSix() {return this.mSix;}

}
