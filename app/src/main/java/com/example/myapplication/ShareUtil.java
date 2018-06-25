package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaolong on 2018/6/19.
 */
public class ShareUtil {

    private final SharedPreferences config;
    private final SharedPreferences.Editor edit;

    public ShareUtil(Context context) {
        config = context.getSharedPreferences("overall_config", Context.MODE_PRIVATE);
        edit = config.edit();
    }

    public void putString(String key, String value) {
        edit.putString(key, value).apply();
    }
    public String getString(String key){
        return config.getString(key,"");
    }
}
