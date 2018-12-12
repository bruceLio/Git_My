package com.example.myapplication;

import android.app.Application;
import com.example.myapplication.util.L;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;



/**
 * Created by xiaolong on 2018/6/7.
 */
public class TestApplication extends Application {
    public static TestApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        PrettyFormatStrategy build = PrettyFormatStrategy.newBuilder().tag(L.LOG_TAG).methodCount(0).showThreadInfo(false).build();
        AndroidLogAdapter adapter = new AndroidLogAdapter(build);
        Logger.addLogAdapter(adapter);

    }



}
