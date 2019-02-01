package com.example.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.myapplication.util.L;
import com.example.myapplication.util.SharedPreferencesUtils;

/**
 * @author lixiaolong
 * @date 2019/1/21
 */
public class LockService extends Service {
    private BroadcastReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e("onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e("onStartCommand");
        SharedPreferencesUtils utils = new SharedPreferencesUtils(TestApplication.app);
        L.e("fromService--" + utils.getBoolean("lock", false) + "");
        if (receiver != null) {
            unregisterLockReceiver(receiver);
        }
        registerLockReceiver();
        return START_STICKY;
    }

    private void unregisterLockReceiver(BroadcastReceiver receiver) {
        unregisterReceiver(receiver);
    }


    private void registerLockReceiver() {
        receiver = new LockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, filter);
    }
}
