package com.example.myapplication;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by xiaolong on 2018/6/7.
 */
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PrettyFormatStrategy build = PrettyFormatStrategy.newBuilder().methodCount(0).showThreadInfo(false).build();
        AndroidLogAdapter adapter = new AndroidLogAdapter(build);
        Logger.addLogAdapter(adapter);


    }


}
