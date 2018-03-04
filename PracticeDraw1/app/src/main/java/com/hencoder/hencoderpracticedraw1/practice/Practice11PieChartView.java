package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class Practice11PieChartView extends View {
    private ArrayList<Item> data;
    private float x, y, r, width, height;
    private final Double PI2 = Math.PI * 2;

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        r = 290;
        setCentrePoint(width*2/5, height*2.3f/5);
        init();

        float curDegree = 0.0f;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        for(Item item : data){
            paint.setColor(item.color);
            if(item.name.equals("Lollipop")){
                setCentrePoint(width*2/5 - 20, height*2.3f/5 -30);
                canvas.drawArc(new RectF(x-r, y-r, x+r, y+r), curDegree, item.degree, true, paint);
                drawText(canvas, item, curDegree + item.degree/2);
                setCentrePoint(width*2/5, height*2.3f/5);
            }else{
                canvas.drawArc(new RectF(x-r, y-r, x+r, y+r), curDegree, item.degree, true, paint);
                drawText(canvas, item, curDegree + item.degree/2);
            }
            curDegree += item.degree;
        }
    }

    private void init(){
        data = new ArrayList<>();
        data.add(new Practice11PieChartView.Item("Froyo", 8, Color.parseColor("#506E7A"), 0, 0));
        data.add(new Practice11PieChartView.Item("Gingerbread", 28, Color.parseColor("#800080"), 9, 9));
        data.add(new Practice11PieChartView.Item("Ice Cream Sandwich", 28, Color.GRAY, 0, 0));
        data.add(new Practice11PieChartView.Item("Jelly Bean", 224, Color.parseColor("#20B2AA"), 0, 0));
        data.add(new Practice11PieChartView.Item("KitKat", 380, Color.parseColor("#00BFFF"), 0, 0));
        data.add(new Practice11PieChartView.Item("Lollipop", 460, Color.RED, 0, 0));
        data.add(new Practice11PieChartView.Item("Marshmallow", 200, Color.parseColor("#FFD700"), 0, 0));

        int sum = 0;
        for(Item item : data){
            sum += item.value;
        }
        for(Item item : data){
            item.degree = item.value * 360.0f / sum;
        }
    }

    public void setCentrePoint(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void drawText(Canvas canvas, Item item, float degree){
        //转换为弧度制
        Double rad = Math.toRadians(degree);
        int len1 = 40;
        int len2 = 80;
        boolean textAtLeft = true;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#D3D3D3"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);

        Path path = new Path();
        path.moveTo(x, y);
        path.rMoveTo(Double.valueOf(r*Math.cos(rad)).floatValue(), Double.valueOf(r*Math.sin(rad)).floatValue());
        path.rLineTo(Double.valueOf(len1*Math.cos(rad)).floatValue(), Double.valueOf(len1*Math.sin(rad)).floatValue());
        path.rLineTo(item.dx, item.dy);
        if(degree > 90 && degree < 270 ){
            path.rLineTo(-len2, 0);
            textAtLeft = false;
        }else{
            path.rLineTo(len2, 0);
            textAtLeft = true;
        }
        canvas.drawPath(path, paint);

        PathMeasure measure = new PathMeasure();
        measure.setPath(path, false);
        float[] position = new float[2];
        measure.getPosTan(measure.getLength(), position, null);

        paint.setTextSize(25.0f);
        paint.setStrokeWidth(2.0f);
        int cap = 10;
        if(textAtLeft){
            canvas.drawText(item.name, position[0] + cap, position[1], paint);
        }else{
            float textLen = paint.measureText(item.name);
            canvas.drawText(item.name, position[0] - textLen - cap, position[1], paint);
        }

    }

    private class Item{
        String name;
        int value;
        int color;
        float degree;
        float dx;
        float dy;

        Item(String name, int value, int color, float dx, float dy){
            this.name = name;
            this.value = value;
            this.color = color;
            this.dx = dx;
            this.dy = dy;
        }
    }
}
