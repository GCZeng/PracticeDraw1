package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    private String[] pieName = {"Froyo", "Gingerbread", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};
    private float[] pieValue = {0f, 7f, 7f, 56f, 110f, 130f, 50f};
    private int[] colors = {Color.WHITE, Color.CYAN, Color.GRAY, Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW};

    private float radius = 200f;
    private float offset = 10f;

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

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        RectF rectF = new RectF();
        rectF.left = 100f;
        rectF.top = 20f;
        rectF.right = rectF.left + radius * 2;
        rectF.bottom = rectF.top + radius * 2;

        RectF rectF2 = new RectF();
        rectF2.left = rectF.left - offset;
        rectF2.top = rectF.top - offset;
        rectF2.right = rectF.right - offset;
        rectF2.bottom = rectF.bottom - offset;

        float startAngle = 0f;
        for (int i = 0; i < pieName.length; i++) {
            paint.setColor(colors[i]);
            switch (i) {
                case 0:
                    canvas.drawArc(rectF, startAngle, pieValue[i], true, paint);
                    break;
                case 5:
                    canvas.drawArc(rectF2, startAngle, pieValue[i], true, paint);
                    break;
                default:
                    canvas.drawArc(rectF, startAngle + 2, pieValue[i] - 2, true, paint);
                    break;
            }

            float centerX = (rectF.left + rectF.right) / 2;
            float centerY = (rectF.top + rectF.bottom) / 2;
            float centerX2 = (rectF2.left + rectF2.right) / 2;
            float centerY2 = (rectF2.top + rectF2.bottom) / 2;

            float angle = startAngle + 1 + pieValue[i] / 2;

            //计算圆弧中点
            Point point = calcArcCenter(centerX, centerY, radius, angle);
            if (i == 5) {
                point = calcArcCenter(centerX2, centerY2, radius, angle);
            }
            Point point2 = calcArcCenter(centerX, centerY, radius + 20, angle);
            if (i == 5) {
                point2 = calcArcCenter(centerX2, centerY2, radius + 20, angle);
            }

            paint.setColor(Color.WHITE);
            paint.setTextSize(16);
            canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);

            if ((angle >= 0 && angle <= 90) || (angle > 270 && angle <= 360)) {
                canvas.drawLine(point2.x, point2.y, point2.x + 20, point2.y, paint);
                canvas.drawText(pieName[i], point2.x + 30, point2.y + 5, paint);
            } else {
                canvas.drawLine(point2.x, point2.y, point2.x - 20, point2.y, paint);
                canvas.drawText(pieName[i], point2.x - paint.measureText(pieName[i]) - 30, point2.y + 5, paint);
            }

            startAngle += pieValue[i];
        }

        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("饼图", 300, 480, paint);
    }

    public Point calcArcCenter(float x, float y, float radius, float angle) {
        float arcX = 0f;
        float arcY = 0f;
        //将角度转换为弧度
        float arcAngle = (float) (Math.PI * angle / 180.0);
        if (angle < 90) {
            arcX = x + (float) (Math.cos(arcAngle)) * radius;
            arcY = y + (float) (Math.sin(arcAngle)) * radius;
        } else if (angle == 90) {
            arcX = x;
            arcY = y + radius;
        } else if (angle > 90 && angle < 180) {
            arcAngle = (float) (Math.PI * (180 - angle) / 180.0);
            arcX = x - (float) (Math.cos(arcAngle)) * radius;
            arcY = y + (float) (Math.sin(arcAngle)) * radius;
        } else if (angle == 180) {
            arcX = x - radius;
            arcY = y;
        } else if (angle > 180 && angle < 270) {
            arcAngle = (float) (Math.PI * (angle - 180) / 180.0);
            arcX = x - (float) (Math.cos(arcAngle)) * radius;
            arcY = y - (float) (Math.sin(arcAngle)) * radius;
        } else if (angle == 270) {
            arcX = x;
            arcY = y - radius;
        } else {
            arcAngle = (float) (Math.PI * (360 - angle) / 180.0);
            arcX = x + (float) (Math.cos(arcAngle)) * radius;
            arcY = y - (float) (Math.sin(arcAngle)) * radius;
        }
        return new Point(arcX, arcY);
    }

    public class Point {
        float x = 0f;
        float y = 0f;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }


    }
}
