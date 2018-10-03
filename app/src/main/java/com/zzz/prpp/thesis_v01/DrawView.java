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
import java.util.LinkedList;
import java.util.List;

public class DrawView extends View {

    private List<Coordinate> mCoordinates = new ArrayList<>();
    private Integer current_position;
    private LinkedList<Vertex> path;
    private Vertex previousVertex;

    int textColor = Color.parseColor("#000000");
    int solidColor = Color.parseColor("#3399ff");
    int positionColor = Color.parseColor("#f4ad42");

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
    public void onDraw(Canvas canvas) {

        setWillNotDraw(false);

        Paint circlePaint = new Paint();
        circlePaint.setColor(solidColor);
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        Paint textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(30);
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        Paint positionPaint = new Paint();
        positionPaint.setColor(positionColor);
        positionPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        double offsetY;
        int radius = 30;
        int h = this.getHeight();
        int w = this.getWidth();
        if (current_position == null) current_position = 2;

        if (!mCoordinates.isEmpty()){
            System.out.println("mCoordinates not empty");
            for (Coordinate c : mCoordinates){
                if (c.getMId()>9) offsetY = 0.5; else offsetY = 0;
                canvas.drawCircle((float) c.getMX() * w / 10, (float) (c.getMY() + offsetY)* h / 10, radius, circlePaint);
                canvas.drawText(String.valueOf(c.getMId()), (float) c.getMX() * w / 10 - radius / 3, (float) (c.getMY() + offsetY) * h / 10 + radius / 3, textPaint);
                if (c.getMId()==current_position) canvas.drawCircle((float) c.getMX() * w / 10, (float) (c.getMY() + offsetY)* h / 10, radius*2, positionPaint);
            }
            if (path != null) {
                previousVertex = null;
                for(Vertex v : path) {
                    if (previousVertex != null) canvas.drawLine(previousVertex.getMX(),previousVertex.getMY(),v.getMX(),v.getMY(),positionPaint);
                        previousVertex = v;
                }
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

    void increaseCurrentPosition(){
        current_position++;
        System.out.println(current_position);
        //invalidate();
    }

    void setCurrentPosition(Integer pos){
        current_position = pos;
        System.out.println("Position Changed: " +current_position);
        //invalidate();
    }

    void setShortestPath(LinkedList<Vertex> p){
        path = p;
        System.out.println("Path changed: " +p.size());
        invalidate();
    }
}
