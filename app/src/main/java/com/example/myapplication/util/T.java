package com.example.myapplication.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xiaolong on 2018/6/8.
 */
public class T {
    public static void show(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
