package com.zzz.prpp.thesis_v01;

import java.util.FormatFlagsConversionMismatchException;

public class Vertex {
    final private int id;
    final private float mX;
    final private float mY;


    public Vertex(Integer id, Float x, Float y) {
        this.id = id;
        this.mX = x;
        this.mY = y;
    }
    public Integer getId() {
        return id;
    }

    public Float getMX() {
        return mX;
    }

    public Float getMY() {
        return mY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (!(id == other.id))
            return false;
        return true;
    }

}
