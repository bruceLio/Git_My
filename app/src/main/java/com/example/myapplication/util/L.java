package com.example.myapplication.util;

import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;

import com.example.myapplication.BuildConfig;

/**
 * Created by xiaolong on 2018/6/7.
 */
public class L {

    public static final String TAG = "xiaolong";
    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static int e(String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(msg)) return Log.e(TAG, "msg is null");
            return Log.e(TAG, msg);

        }
        return -1;
    }
}
