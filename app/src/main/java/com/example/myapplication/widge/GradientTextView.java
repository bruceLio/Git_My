package com.example.myapplication.widge;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.myapplication.R;

@SuppressLint("AppCompatCustomView")
public class GradientTextView extends TextView {
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 宽度
     */
    private int mViewWidth;
    /**
     * 线性渐变对象
     */
    private LinearGradient mLinearGradient;
    /**
     * 矩阵对象
     */
    private Matrix mGradientMatrix;
    /**
     * 平移距离
     */
    private int mTranslate;
    private int mColor1;
    private int mColor2;
    private int mColor3;
    private ObjectAnimator animator;

    public GradientTextView(Context context) {
        super(context, null);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.GradientTextView);
        mColor1 = typedArray.getColor(R.styleable.GradientTextView_color1, Color.BLACK);
        mColor2 = typedArray.getColor(R.styleable.GradientTextView_color2, Color.WHITE);
        mColor3 = typedArray.getColor(R.styleable.GradientTextView_color3, Color.BLACK);
    }

    /**
     * 测量出文本宽度后,再初始化画笔等基础设置
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            if (animator != null) {
                animator.cancel();
                animator.start();
            } else {
                start();
            }
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(
                        -getWidth(),
                        0,
                        0,
                        0,
                        new int[]{mColor1, mColor2, mColor3},
                        new float[]{0, 0.5f, 1},
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制文字之前
        super.onDraw(canvas);
        //绘制文字之后
        if (mGradientMatrix != null) {
            mGradientMatrix.setTranslate(2 * mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
        }
    }

    public void setTranslate(float translate) {
        mTranslate = (int) translate;
        invalidate();
    }

    public void start() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(this, "translate", 0, getWidth());
            animator.setRepeatCount(-1);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.setDuration(1500);
        }
        animator.start();
    }

    public void cancle() {
        if (animator != null) {
            animator.cancel();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancle();
    }
}