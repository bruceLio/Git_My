package com.example.myapplication.util;

import android.graphics.drawable.GradientDrawable;

/**
 * @author lixiaolong
 * @date 2019/3/15
 */
public class DrawableUtils {

    public static GradientDrawable getRectangleDrawable(int color){
        GradientDrawable drawable =new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(color);
        drawable.setCornerRadius(300);
        return drawable;
    }
}
