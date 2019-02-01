package com.example.myapplication.widge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.myapplication.util.Util;


/**
 * @author lixiaolong
 * @date 2019/1/3
 */
public class NetView extends View {
    private Paint mPaint;
    private Path mPath;
    private int[] values = {100, 100, 100, 100, 100, 50};
    private int maxValue = 100;
    private int maxLine = maxValue * 2;
    private int mHeight;
    private int CIRCLE_COLOR = Color.WHITE;
    private int NET_COLOR = 0x99ffffff;
    private int FILL_COLOR = 0xff2298ff;
    private int LINE_COLOR = 0x99ffffff;
    private int padding = 15;
    private int maxWidth;
    private int maxHeight;
    private int textSize = 30;
    private String[] textArr = new String[]{"top", "bottom", "topLeft", "bottomLeft", "topRight", "bottomRight"};

    public NetView(Context context) {
        this(context, null);
    }

    public NetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.maxWidth = w - padding * 2;
        this.maxHeight = h - padding * 2;
        this.mHeight = (int) ((h - padding * 2) - textSize * 2.5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        // 设置画笔宽度为1
        mPaint.setStrokeWidth(10);
        // 移动画布到屏幕中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 坐标以X轴翻转
//        canvas.scale(1, -1);
        mPaint.setColor(FILL_COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, mHeight / 2, mPaint);
        drawNet(canvas, values);
        drawLines(canvas);
        drawCircle(canvas);
        drawText(canvas);

    }

    private void drawCircle(Canvas canvas) {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(CIRCLE_COLOR);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(0, 0, mHeight / 2, mPaint);
        mPaint.setStrokeWidth(5);
        canvas.drawCircle(0, 0, mHeight / 3, mPaint);
        canvas.drawCircle(0, 0, mHeight / 6, mPaint);

    }

    private void drawLines(Canvas canvas) {
        for (int i = 0; i < 6; i++) {
            mPaint.setStrokeWidth(4);
            mPaint.setColor(LINE_COLOR);
            canvas.drawLine(0, 0, 0, mHeight / 2, mPaint);
//            mPaint.setColor(Color.BLACK);
//            mPaint.setStrokeWidth(20);
//            canvas.drawPoint(0, values[i] * mHeight / maxLine, mPaint);
            canvas.rotate(60);
        }
    }


    private void point2Line(Canvas canvas, Point[] points) {
        Path path = new Path();
        Paint paint = new Paint();
        paint.setColor(NET_COLOR);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(4);

        path.moveTo(points[0].x, points[0].y);
        for (int i = 1; i < points.length; i++) {
            path.lineTo(points[i].x, points[i].y);
        }
        // 闭合线路
        path.close();
        canvas.drawPath(path, paint);
    }

    private void init() {
        textSize = Util.dip2px(getContext(), 12);
        // 实例化画笔
        mPaint = new Paint();

        // 设置画笔样式为描边模式
        mPaint.setStyle(Paint.Style.STROKE);

        // 开启抗锯齿
        mPaint.setAntiAlias(true);
        // 实例化坐标轴path
        mPath = new Path();
        // 移动至坐标原点
        mPath.moveTo(0, 0);

    }

    private Point getRotatePoint(int index, int value) {
        double degrees = Math.toRadians((index) * 60);
        Point point = new Point();
        point.x = -(int) ((Math.sin(degrees) * value * mHeight / maxLine));
        point.y = (int) ((Math.cos(degrees) * value * mHeight / maxLine));
        return point;
    }

    private void drawNet(Canvas canvas, int[] values) {
        Point[] points = new Point[values.length];
        // 绘制顶点
        for (int i = 0; i < values.length; i++) {
            Point point = getRotatePoint(i, values[i]);
            points[i] = point;
        }
        // 连接各个顶点
        point2Line(canvas, points);
    }

    private void drawText(Canvas canvas) {
//        canvas.restoreToCount(1);
        canvas.translate(-getWidth() / 2, -getHeight() / 2);
        // 坐标以X轴翻转
//        canvas.scale(1, -1);
        mPaint.setTextSize(textSize);
        mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        mPaint.getTextBounds(textArr[0], 0, textArr[0].length(), rect);
        int textHeight = rect.height();//文本的高度
        int topY = (int) (getHeight() / 2 - mHeight / 2 * Math.sin(Math.toRadians(30))) - textHeight / 2;
        int bottomY = (int) (getHeight() / 2 + mHeight / 2 * Math.sin(Math.toRadians(30))) + textHeight / 2;
        int leftX = (int) (getWidth() / 2 - mHeight / 2 * Math.sin(Math.toRadians(60))) - textHeight / 2;
        int rightX = (int) (getWidth() / 2 + mHeight / 2 * Math.sin(Math.toRadians(60))) + textHeight / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(textArr[0], getWidth() / 2, (getHeight() - mHeight) / 2 - textHeight / 2, mPaint);
        canvas.drawText(textArr[1], getWidth() / 2, (getHeight() + mHeight) / 2 + textHeight+2, mPaint);
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(textArr[2], leftX, topY + textHeight / 2, mPaint);
        canvas.drawText(textArr[3], leftX, bottomY, mPaint);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(textArr[4], rightX, topY + textHeight / 2, mPaint);
        canvas.drawText(textArr[5], rightX, bottomY, mPaint);
    }

    public void setText(String[] arr) {
        this.textArr = arr;
        invalidate();
    }

}
