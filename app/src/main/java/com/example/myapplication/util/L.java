package com.example.myapplication.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by xiaolong on 2018/6/7.
 */
public class L {

    public static final String TAG="xiaolong";
    public static int e(String msg){
        if(TextUtils.isEmpty(msg))return Log.e(TAG,"msg is null");
        return Log.e(TAG,msg);
    }
}
