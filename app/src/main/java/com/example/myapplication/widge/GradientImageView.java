package com.example.myapplication.widge;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * @author lixiaolong
 * @date 2019/1/23
 */
public class GradientImageView extends android.support.v7.widget.AppCompatImageView {
    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;

    public GradientImageView(Context context) {
        super(context);
    }

    public GradientImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = new Paint();
                mLinearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth / 10,
                        0,
                        new int[]{Color.WHITE, 0xffffff, Color.WHITE},
                        new float[]{0, 0.5f, 1.0f},
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制文字之后
        if (mGradientMatrix != null) {
            canvas.drawPaint(mPaint);
            mTranslate += mViewWidth / 10;
            if (mTranslate > 2 * mViewWidth) {//决定文字闪烁的频繁:快慢
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(50);
        }
        super.onDraw(canvas);

    }
}
