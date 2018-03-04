package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Practice10HistogramView extends View {
    private ArrayList<Item> data;
    private float x, y, width, height;

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        int green = Color.parseColor("#32CD32");
        int white = Color.WHITE;

        setZeroPoint(width/10, height*3/4);
        drawCoordinate(canvas);

        init();

        int cap = 119;
        for(int i = 0; i < data.size(); i++){
            float index = x + cap * (i+1) - 42;

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(white);
            paint.setTextSize(25.0f);
            String text = data.get(i).name;
            float textLen = paint.measureText(text);
            canvas.drawText(text, index - textLen/2, y + 20, paint);

            paint.setColor(green);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(100.0f);
            Path path = new Path();
            path.moveTo(index, y);
            path.rLineTo(0, - data.get(i).value);
            canvas.drawPath(path, paint);
        }


    }

    private void init(){
        data = new ArrayList<>();
        data.add(new Item("Froyo", 2));
        data.add(new Item("GB", 28));
        data.add(new Item("ICS", 28));
        data.add(new Item("JB", 224));
        data.add(new Item("KitKat", 400));
        data.add(new Item("L", 440));
        data.add(new Item("M", 200));
    }

    private void drawCoordinate(Canvas canvas){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(x, y);
        path.rLineTo(0, -height*7/10);
        path.moveTo(x, y);
        path.rLineTo(width*8/10, 0);

        canvas.drawPath(path, paint);

        paint.setStrokeWidth(3.0f);
        paint.setTextSize(50.0f);
        canvas.drawText("直方图", width/2 - 90, (height + y)/2, paint);
    }

    public void setZeroPoint(float x, float y){
        this.x = x;
        this.y = y;
    }

    private class Item{
        String name;
        int value;

        Item(String name, int value){
            this.name = name;
            this.value = value;
        }
    }
}
