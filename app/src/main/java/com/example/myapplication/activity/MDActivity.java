package com.example.myapplication.activity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SystemBarTintManager;
import com.example.myapplication.adapter.SimpleAdapter;
import com.example.myapplication.adapter.SimpleRecycleViewAdapter;
import com.example.myapplication.util.L;
import com.example.myapplication.util.T;
import com.jude.easyrecyclerview.EasyRecyclerView;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MDActivity extends BaseActivity {

    private HomeReceiver homeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);
        CollapsingToolbarLayout test = findViewById(R.id.test);
        test.setContentScrimColor(Color.parseColor("#00000000"));
        RecyclerView rv = findViewById(R.id.rv);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("position" + i);
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        SimpleAdapter adapter = new SimpleAdapter(this, list);
        rv.setAdapter(adapter);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        homeReceiver = new HomeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(homeReceiver, filter);
//        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
//        systemBarTintManager.setStatusBarTintEnabled(true);//显示状态栏
//        systemBarTintManager.setStatusBarTintColor(R.color.colorPrimaryDark);//设置状态栏颜色
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(homeReceiver);
    }

    private void onHomeClick() {
        Intent intent = new Intent(this, MDActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (KeyEvent.KEYCODE_HOME == keyCode) {
            L.e("home");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public class HomeReceiver extends BroadcastReceiver {
        static public final String SYSTEM_DIALOG_REASON_KEY = "reason";
        static public final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        static public final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context arg0, Intent arg1) {


            String action = arg1.getAction();
            //按下Home键会发送ACTION_CLOSE_SYSTEM_DIALOGS的广播
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = arg1.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null) {
                    if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                        L.e("按了home");
                        for (int i = 0; i < 10; i++) {
                            onHomeClick();
                        }
                    }
                }
            }
        }
    }
}
