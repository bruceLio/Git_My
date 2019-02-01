package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaolong on 2018/6/19.
 */
public class SharedPreferencesUtils {

    private final SharedPreferences config;
    private final SharedPreferences.Editor edit;

    public SharedPreferencesUtils(Context context) {
        config = context.getSharedPreferences("overall_config", Context.MODE_PRIVATE);
        edit = config.edit();
    }

    public void putString(String key, String value) {
        edit.putString(key, value).apply();
    }

    public String getString(String key) {
        return config.getString(key, "");
    }

    public void putBoolean(String key, boolean value) {
        edit.putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return config.getBoolean(key, defaultValue);
    }
}
