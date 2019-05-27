package com.example.myapplication.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class ItemView extends View {
    private int maxHeight;
    private int minHeight = 100;
    private int curHeight =minHeight;
    private int lineWidth;
    private Paint mPaint;
    private  int speed =5;

    public ItemView(Context context) {
        super(context);
        init();
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#ff3d44"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(lineWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        maxHeight = h;
        lineWidth = w;
        mPaint.setStrokeWidth(lineWidth);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, maxHeight, 0, curHeight, mPaint);
        catLineHeight();
        postInvalidate();

    }

    boolean add = true;

    private void catLineHeight() {
        if (curHeight <= 0) {
            add = true;
        } else if (curHeight > maxHeight-minHeight) {
            add = false;
        }
        if (add) curHeight += speed;
        else {
            curHeight -= speed;
        }
    }
}
