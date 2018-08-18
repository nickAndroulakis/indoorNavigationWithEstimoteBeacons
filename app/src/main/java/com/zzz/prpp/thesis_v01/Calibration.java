package com.zzz.prpp.thesis_v01;

public class Calibration {

    private Double mOne;
    private Double mTwo;
    private Double mThree;
    private Double mFour;
    private Double mFive;
    private Double mSix;

    //constructor
    public Calibration(Double one, Double two, Double three, Double four, Double five, Double six) {
        this.mOne = one;
        this.mTwo = two;
        this.mThree = three;
        this.mFour = four;
        this.mFive = five;
        this.mSix = six;
    }

    public Double getmOne() {return this.mOne;}
    public Double getmTwo() {return this.mTwo;}
    public Double getmThree() {return this.mThree;}
    public Double getmFour() {return this.mFour;}
    public Double getmFive() {return this.mFive;}
    public Double getmSix() {return this.mSix;}
}
