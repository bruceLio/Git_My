package com.example.myapplication.util;

import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;

import com.example.myapplication.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by xiaolong on 2018/6/7.
 */
public class L {

    public static final String LOG_TAG = "xiaolong";
    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static void e(String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(msg)) {
                Logger.e("msg is null");
            }
            Logger.e(msg);

        }
    }
}
