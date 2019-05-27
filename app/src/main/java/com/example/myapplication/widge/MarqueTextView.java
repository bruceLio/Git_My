package com.example.myapplication.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author lixiaolong
 * @date 2019/4/25
 */
public class MarqueTextView extends View {
    private TextPaint mPaint;
    private String mText = "abcdefg";

    public MarqueTextView(Context context) {
        super(context);
        init();
    }

    public MarqueTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarqueTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new TextPaint();
        mPaint.setColor(Color.parseColor("#ff3d44"));
        mPaint.setTextSize(50);
    }

    public void setText(String text) {
        mText = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, 0, 0, mPaint);
    }
}
