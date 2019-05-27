package com.example.myapplication.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.myapplication.R;

/**
 * @author lixiaolong
 * @date 2019-05-20
 */
public class NotificationManager {

    public static void makeNotifation(Context context) {
        NotificationChannel channel = null;
        android.app.NotificationManager systemService = (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("123", "avb", android.app.NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            systemService.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "123");
        builder.setSmallIcon(R.drawable.ic_btn_guize);
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_notificaiton);
        builder.setContent(new RemoteViews(context.getPackageName(), R.layout.layout_notificaiton));
        systemService.notify(123, builder.build());
    }
}
