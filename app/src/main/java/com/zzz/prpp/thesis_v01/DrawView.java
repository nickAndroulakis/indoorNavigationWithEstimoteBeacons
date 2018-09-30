package com.zzz.prpp.thesis_v01;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {

    private List<Coordinate> mCoordinates = new ArrayList<>();

    int textColor = Color.parseColor("#000000");
    int solidColor = Color.parseColor("#3399ff");

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(solidColor);
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        Paint textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(30);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        mCoordinates.add(new Coordinate(1,5,2));
        mCoordinates.add(new Coordinate(2,5,3));
        mCoordinates.add(new Coordinate(3,7,3));
        mCoordinates.add(new Coordinate(4,1,4));
        mCoordinates.add(new Coordinate(5,1,5));
        mCoordinates.add(new Coordinate(6,3,5));
        mCoordinates.add(new Coordinate(7,5,5));
        mCoordinates.add(new Coordinate(8,7,5));
        mCoordinates.add(new Coordinate(9,9,5));
        mCoordinates.add(new Coordinate(10,5,6));
        mCoordinates.add(new Coordinate(11,7,6));
        mCoordinates.add(new Coordinate(12,9,6));
        mCoordinates.add(new Coordinate(13,5,7));
        mCoordinates.add(new Coordinate(14,7,7));
        mCoordinates.add(new Coordinate(15,5,8));
        mCoordinates.add(new Coordinate(16,7,8));

        double offsetY;
        int radius = 30;
        int h = this.getHeight();
        int w = this.getWidth();
        if (mCoordinates != null){
            for (Coordinate c : mCoordinates){
                if (c.getMId()>9) offsetY = 0.5; else offsetY = 0;
                canvas.drawCircle((float) c.getMX() * w / 10, (float) (c.getMY() + offsetY)* h / 10, radius, circlePaint);
                canvas.drawText(String.valueOf(c.getMId()), (float) c.getMX() * w / 10 - radius / 3, (float) (c.getMY() + offsetY) * h / 10 + radius / 3, textPaint);
            }
        }
        super.draw(canvas);
    }

    void setCoordinates(List<Coordinate> coordinates){
        mCoordinates = coordinates;
        //notifyDataSetChanged();
    }

}
