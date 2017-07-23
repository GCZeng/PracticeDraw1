package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    private Path path = new Path();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice9DrawPathView(Context context) {
        super(context);
        init();
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        RectF rectF = new RectF();
        rectF.left = 200f;
        rectF.top = 100f;
        rectF.right = 300f;
        rectF.bottom = 200f;
        path.addArc(rectF, 140, 220);


        rectF.left = 300f;
        rectF.top = 100f;
        rectF.right = 400f;
        rectF.bottom = 200f;
        path.arcTo(rectF, 180, 220);


        path.lineTo(300,280);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形

        paint.setColor(Color.BLACK);

        canvas.drawPath(path, paint);
    }
}
