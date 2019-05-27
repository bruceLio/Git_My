package com.example.myapplication.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.myapplication.BuildConfig;

/**
 * Created by xiaolong on 2018/6/7.
 */
public class L {

    public static final String LOG_TAG = "xiaolong";
    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static void e(String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(msg)) {
                Log.e(LOG_TAG, "msg is null");
            } else {
                Log.e(LOG_TAG, msg);
            }

        }
    }


}
