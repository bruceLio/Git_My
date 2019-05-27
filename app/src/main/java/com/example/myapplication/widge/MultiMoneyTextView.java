package com.example.myapplication.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaolong
 * @date 2019/02/12
 */
public class MultiMoneyTextView extends LinearLayout {
    private Context mContext;
    private List<MoneyTextView> moneyTextViewList = new ArrayList<>();
    private int mTextColor = 0xffffffff;
    private float mTextSize = dp2px(30);
    private Paint mPaint = new Paint();
    private Typeface defaultBold = Typeface.DEFAULT_BOLD;

    public void setTextSize(int textSize, int textColor, Typeface typeface) {
        if (mPaint != null) {
            mTextSize = textSize;
            mTextColor = textColor;
            defaultBold = typeface;
            mPaint.setTextSize(mTextSize);
            mPaint.setTypeface(defaultBold);
            mPaint.setColor(mTextColor);
        }
    }

    public int getCurMoney() {
        return curMoney;
    }

    private int curMoney = 0;

    public MultiMoneyTextView(Context context) {
        this(context, null);
    }

    public MultiMoneyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiMoneyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    public void setNumber(int to) {
        if (curMoney == to && to != 0) {
            return;
        }
        if (curMoney > to) {
            moneyTextViewList.clear();
            removeAllViews();
        }
        boolean flag = false;
        List<Integer> targetList = getNumList(to);
        List<Integer> curList = getNumList(curMoney);
        if (curList.size() != targetList.size() && curMoney != 0) {
            moneyTextViewList.clear();
            removeAllViews();
            flag = true;
        }
        curMoney = to;
        int size = targetList.size();
        if (moneyTextViewList.size() == 0) {
            for (int i = 0; i < size; i++) {
                Integer targetNum = targetList.get(size - i - 1);
                MoneyTextView moneyTextView = new MoneyTextView(mContext);
                moneyTextView.setNum(targetNum, flag);
                moneyTextViewList.add(moneyTextView);
                addView(moneyTextView);
                addDot(size - i - 1);

            }
        } else {
            for (int i = 0; i < size; i++) {
                Integer targetNum = targetList.get(size - i - 1);
                if (moneyTextViewList.size() > i && moneyTextViewList.get(i) != null) {
                    moneyTextViewList.get(i).setNum(targetNum, i * 20);
                } else {
                    MoneyTextView moneyTextView = new MoneyTextView(mContext);
                    moneyTextView.setNum(targetNum, i * 20);
                    moneyTextViewList.add(moneyTextView);
                    addView(moneyTextView);
                    addDot(size - i - 1);
                }

            }
        }

    }

    private void addDot(int i) {
        if (i % 3 == 0 && i != 0) {
            MoneyTextView dot = new MoneyTextView(mContext);
            dot.setText(",");
            addView(dot);
        }
    }

    private List<Integer> getNumList(int number) {
        List<Integer> list = new ArrayList<>();
        if (number == 0) {
            list.add(0);
            return list;
        }
        while (number > 0) {
            int i = number % 10;
            list.add(i);
            number /= 10;
        }
        return list;
    }

    public class MoneyTextView extends View {
        private Rect bounds = new Rect();
        private int mTextHeight = 0;
        private String mText;
        private int currentNum;
        private int mDeltaNum;
        private float offSet = 0;
        private int mTarget;
        private boolean showAnim = true;
        private final float DEFAULT_VELOCITY = 0.1f;
        private Interpolator interpolator = new AccelerateDecelerateInterpolator();

        public MoneyTextView(Context context) {
            super(context);
            mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mTextColor);
            mPaint.setTypeface(defaultBold);
        }

        public void setNum(int from, int to, long delay) {
            if (from != to) {
                currentNum = from;
                mDeltaNum = to - from < 0 ? to - from + 10 : to - from;
                mTarget = to;
                postInvalidateDelayed(delay);
            }
        }

        public void setNum(final int to, long delay) {
            setNum(currentNum, to, delay);
            showAnim = true;
        }

        public void setNum(int to, boolean showAnim) {
            if (showAnim) {
                setNum(currentNum, to, 0);
            } else {
                this.showAnim = showAnim;
                currentNum = to;
                mTarget = to;
                invalidate();
            }
        }

        public void setText(String text) {
            this.mText = text;
            requestLayout();
            invalidate();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width;
            mPaint.getTextBounds("0", 0, 1, bounds);
            if (mText != null) {
                width = (int) (bounds.width() / 2);
            } else {
                width = (int) (bounds.width() * 1.1);
            }
            mTextHeight = bounds.height();
            double height = mTextHeight * 2;
            setMeasuredDimension(width, (int) height);
        }

        private double getPercent() {
            int progress = mTarget - currentNum;
            if (progress < 0) {
                progress = progress + 10;
            }
            return 1 - progress * 1.0 / mDeltaNum;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (offSet <= 1) {
                    offSet += DEFAULT_VELOCITY * (interpolator.getInterpolation((float) getPercent() * 2) + 0.2);
//                    offSet += (mDeltaNum + 3) * 0.01;//平滑速度
                } else {
                    add();
                    offSet = 0;
                }
                invalidate();
            }
        };

        private void add() {
            currentNum++;
            if (currentNum == 10) {
                currentNum = 0;
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (mText != null) {
                canvas.drawText(mText, getWidth() / 2, (float) (mTextHeight * 3 / 2), mPaint);
                return;
            }
            if (!showAnim) {
                currentNum = mTarget;
                drawSelf(canvas);
                return;
            }
            if (currentNum != mTarget) {
                post(runnable);
            }
            canvas.translate(0, -(float) (offSet * 1.52 * mTextHeight));
            drawSelf(canvas);
            drawNext(canvas);
        }

        private void drawNext(Canvas canvas) {
            int next = currentNum + 1 == 10 ? 0 : currentNum + 1;
            canvas.drawText(next + "", getWidth() / 2, (float) (mTextHeight * 3.1), mPaint);
        }

        private void drawSelf(Canvas canvas) {
            canvas.drawText(currentNum + "", getWidth() / 2, mTextHeight * 3 / 2, mPaint);
        }


    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
}
