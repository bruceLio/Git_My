package com.example.myapplication.widge;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author lixiaolong
 * @date 2019/4/17
 */
public class MoveAbleView extends View {
    private boolean dragging = false;
    private float downX;
    private float downY;

    public MoveAbleView(Context context) {
        super(context);
        init();
    }

    public MoveAbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MoveAbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dragging = true;
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                dragging = false;
                setTranslationX(0);
                break;
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (dragging) {
                    float dx = x - downX;
                    float dy = y - downY;
                    float translationY = getTranslationY();
                    float translationX = getTranslationX();
                    setTranslationX(translationX + dx);
                    setTranslationY(translationY + dy);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {

        super.onDetachedFromWindow();
    }
}
