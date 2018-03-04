package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    final float SQRT2 = (float) Math.sqrt(2);

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int r = 75;
        float x1 = width/2 - (1 + SQRT2/2) * r;
        int x2 = width/2 + r;
        int y = height*2/5;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        Path path = new Path();
        path.addArc(new RectF(width/2, y-r, width/2 + 2*r, y+r), 180, 225);
        path.rLineTo(-(1 + SQRT2/2)*r, (1 + SQRT2/2) * r);
        path.rLineTo(-(1 + SQRT2/2)*r, -(1 + SQRT2/2)*r);
        path.addArc(new RectF(width/2 -2*r, y-r, width/2, y+r), 135, 225);

        canvas.drawPath(path, paint);
    }
}
