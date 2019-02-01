package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.myapplication.activity.MDActivity;
import com.example.myapplication.util.L;

/**
 * @author lixiaolong
 * @date 2019/1/21
 */
public class LockReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        L.e(intent.getAction());
        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_OFF:
                Intent lockActivity = new Intent(context, MDActivity.class);
                lockActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(lockActivity);
                break;
        }
    }
}
