package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    private String[] xName = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
    private int[] xValue = {1, 10, 10, 100, 200, 300, 150};

    private int x = 50;
    private int y = 400;

    private int width = 70;

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

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1);

        //坐标系
        canvas.drawLine(x, x, x, y, paint);
        canvas.drawLine(x, y, y + 300, y, paint);

        paint.setTextSize(30);
        canvas.drawText("直方图", 300, 480, paint);

        int x = 0;
        for (int i = 0; i < xName.length; i++) {
            x += 80;
            paint.setColor(Color.GREEN);
            Rect rect = new Rect();
            rect.left = x;
            rect.top = y - xValue[i];
            rect.right = x + width;
            rect.bottom = y;
            canvas.drawRect(rect, paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(18);

            canvas.drawText(xName[i], rect.left+(width - paint.measureText(xName[i]))/2, rect.bottom + 20, paint);

        }

    }
}
