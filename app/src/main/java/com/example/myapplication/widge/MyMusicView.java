package com.example.myapplication.widge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.util.L;

import static com.example.myapplication.util.Util.dp2px;

public class MyMusicView extends LinearLayout {
    private int count = 4;
    private boolean playing = true;
    private int mLineWidth;
    private int mLineHeight;
    private int lineColor;
    private Interpolator interpolator = new AccelerateDecelerateInterpolator();
    private String position;

    public void setPlaying(boolean playing) {
        this.playing = playing;
        if (playing) {
            for (int i = 0; i < count; i++) {
                getChildAt(i).invalidate();
            }
        }
    }

    public void toggle() {
        setPlaying(!playing);
    }

    public MyMusicView(Context context) {
        super(context);
        initChild(null);
    }

    public MyMusicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initChild(attrs);

    }

    public MyMusicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initChild(attrs);

    }

    private void initChild(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyMusicView);
            mLineWidth = typedArray.getDimensionPixelSize(R.styleable.MyMusicView_mvLineWidth, dp2px(getContext(), 5));
            mLineHeight = typedArray.getDimensionPixelOffset(R.styleable.MyMusicView_mvLineHeight, dp2px(getContext(), 40));
            lineColor = typedArray.getColor(R.styleable.MyMusicView_mvLineColor, Color.parseColor("#ff3d44"));
            typedArray.recycle();
        }
        setOrientation(HORIZONTAL);
        for (int i = 0; i < count; i++) {
            ItemView itemView = new ItemView(getContext());
            LayoutParams layoutParams = new LayoutParams(mLineWidth, mLineHeight);
            layoutParams.rightMargin = mLineWidth;
            itemView.setLayoutParams(layoutParams);
            int curHeght = 0;
            if (i == 0) {
                curHeght = mLineHeight;
            }
            if (i == 1) {
                curHeght = (int) (mLineHeight * 0.4f);
            }
            if (i == 2) {
                curHeght = (int) (mLineHeight * 0.8f);
            }
            if (i == 3) {
                curHeght = (int) (mLineHeight * 0.5f);
            }
            itemView.setCurHeight(curHeght);
            addView(itemView);
        }
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public class ItemView extends View {
        private int maxHeight;
        private int minHeight;

        public void setCurHeight(int curHeight) {
            this.curHeight = curHeight;
        }

        private int curHeight;
        private int lineWidth = 0;
        private Paint mPaint;
        private int speed = 5;
        int offset = 0;

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
            mPaint.setColor(lineColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(lineWidth);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            maxHeight = h;
            minHeight = (int) (maxHeight * 0.3);
            lineWidth = w;
            offset = (curHeight - minHeight) * 100 / (maxHeight - minHeight);
            mPaint.setStrokeWidth(lineWidth * 2);
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawLine(0, maxHeight, 0, maxHeight - curHeight, mPaint);
            catLineHeight();
            if (playing) {
                postInvalidate();
            }

        }


        private void catLineHeight() {
            if (offset > 200) {
                offset = 0;
            }
            offset += speed;
            int maxDiff = maxHeight - minHeight;
            int diff = (int) (maxDiff * interpolator.getInterpolation(offset / 100f));
            curHeight = minHeight + diff;
        }
    }
}
