package com.zzz.prpp.thesis_v01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawNodesView extends View {

    private List<Coordinate> mCoordinates = new ArrayList<>();

    int textColor = Color.parseColor("#000000");
    int solidColor = Color.parseColor("#3399ff");

    public DrawNodesView(Context context) {
        super(context);
    }

    public DrawNodesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawNodesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas) {

        setWillNotDraw(false);

        Paint circlePaint = new Paint();
        circlePaint.setColor(solidColor);
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        Paint textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(30);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        float offsetY;
        float previousOffsetY = 0f;
        int radius = 30;
        int h = this.getHeight();
        int w = this.getWidth();

        if (!mCoordinates.isEmpty()) {
            System.out.println("mCoordinates not empty");
            for (Coordinate c : mCoordinates) {
                if (c.getMId() > 9) offsetY = 0.5f;
                else offsetY = 0f;
                canvas.drawCircle((float) c.getMX() * w / 10, (float) (c.getMY() + offsetY) * h / 10, radius, circlePaint);
                canvas.drawText(String.valueOf(c.getMId()), (float) c.getMX() * w / 10 - radius / 3, (float) (c.getMY() + offsetY) * h / 10 + radius / 3, textPaint);
            }
        }

        System.out.println("I WILL DRAW NOW");
        super.onDraw(canvas);
    }

    void setCoordinates(List<Coordinate> coordinates){
        System.out.println("Coordinates size: "+coordinates.size());
        mCoordinates = coordinates;
        //invalidate();
    }

}
