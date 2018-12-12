package com.example.myapplication.widge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;

/**
 * @author lixiaolong
 * @date 2018/11/12
 */
@SuppressLint("AppCompatCustomView")
public class MatrixView  extends android.support.v7.widget.AppCompatImageView{
    private Bitmap bmp;

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public void setMatrix(Matrix maxtrix) {
        this.mMatrix = maxtrix;
        invalidate();
    }

    private Matrix mMatrix;

    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bmp!=null&& mMatrix !=null){
            canvas.drawBitmap(bmp, mMatrix,null);
        }

    }
}
