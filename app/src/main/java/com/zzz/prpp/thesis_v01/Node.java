package com.zzz.prpp.thesis_v01;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity(tableName="Node_table")
public class Node {

    @PrimaryKey(autoGenerate = true)
    private Integer mId;
    @NonNull
    @ColumnInfo(name = "node")
    private Integer mNumber;
    private Double mOne;
    private Double mTwo;
    private Double mThree;
    private Double mFour;
    private Double mFive;
    private Double mSix;


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

    public String getNode(){
        if (mOne==null)
            mOne= 10.0;
        if (mTwo==null)
            mTwo= 10.0;
        if (mThree==null)
            mThree= 10.0;
        if (mFour==null)
            mFour= 10.0;
        if (mFive==null)
            mFive= 10.0;
        if (mSix==null)
            mSix= 10.0;

        return mNumber.toString() + "-" + mOne.toString() + "," + mTwo.toString() + "," + mThree.toString() + "," + mFour.toString() + "," + mFive.toString() + "," + mSix.toString() ; }


    //getters

    public Integer getMId() {return this.mId;}
    @NonNull
    public Integer getMNumber() {return this.mNumber;}
    public Double getMOne() {return this.mOne;}
    public Double getMTwo() {return this.mTwo;}
    public Double getMThree() {return this.mThree;}
    public Double getMFour() {return this.mFour;}
    public Double getMFive() {return this.mFive;}
    public Double getMSix() {return this.mSix;}

    //setters
    public void setMId(Integer mId) {this.mId = mId;}


}
