package com.example.myapplication.util;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author lixiaolong
 * @date 2018/9/13
 */
public class BitmapUtils {
    public void fenxiBitmap(Bitmap bitmap){
        fenxiBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());
    }

    public void  fenxiBitmap(Bitmap bmp,int fromX, int formY, int toX, int toY){


        int[] pixels=new int[bmp.getWidth()*bmp.getHeight()];
        bmp.getPixels(pixels,0,bmp.getWidth(),fromX,formY,toX-fromX,toY-formY);
        HashMap<Integer, Integer> colors = new HashMap<>();
        for (int pixel : pixels) {
            Integer num = colors.get(pixel);
            if (num == null) {
                colors.put(pixel, 1);
            } else {
                num += 1;
                colors.put(pixel, num);
            }
        }
        TreeMap<Integer, Integer> sortedColors = new TreeMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : colors.entrySet()) {
            sortedColors.put(entry.getValue(), entry.getKey());
        }
        for (Map.Entry<Integer, Integer> entry : sortedColors.entrySet()) {
            result.add(entry.getValue());
            L.e( "run: color:"+entry.getValue()+",count:"+entry.getKey());
        }
    }

    public String int2Rgb(int color){
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        return "R"+String.valueOf(red)+"G"+String.valueOf(green)+"B"+String.valueOf(blue);
    }

}
