package com.example.myapplication;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.alibaba.fastjson.JSON;
import com.example.myapplication.activity.BaseActivity;
import com.example.myapplication.activity.MDActivity;
import com.example.myapplication.bean.Abc;
import com.example.myapplication.bean.Area;
import com.example.myapplication.bean.NewArea;
import com.example.myapplication.bean.Zone;
import com.example.myapplication.helper.NotificationManager;
import com.example.myapplication.util.AssetsUtils;
import com.example.myapplication.util.FileUtils;
import com.example.myapplication.util.L;
import com.example.myapplication.util.PermissionsChecker;
import com.example.myapplication.util.T;
import com.example.myapplication.util.Util;
import com.example.myapplication.widge.MultiMoneyTextView;
import com.example.myapplication.widge.MyMusicView;
import com.example.myapplication.widge.ScrollNumber;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private final int START_COLOR = Color.parseColor("#ff834df5");
    private final int END_COLOR = Color.parseColor("#ff2bbee8");
    public static final String PERMISSION_SAY_HELLO = "examples.ouc.com.checkpermissioncode.permission.SAY_HELLO";
    MultiMoneyTextView tv;
    ScrollNumber tv1;
    View abcd;
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };
    private String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission_group.PHONE,
            Manifest.permission.READ_PHONE_STATE};
    private View main;
    private MultiMoneyTextView stv;
    private String TAG = "xiaolong";
    private View cv;
    private int height;
    MyMusicView mv;
    private Interpolator interpolator = new AccelerateDecelerateInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.main_content);
        findViewById(R.id.bt).setOnClickListener(this);
//        for (int i = 0; i < 200; i++) {
//            L.e(interpolator.getInterpolation(i/100f)+"");
//        }
//        requestPermissions(new String[]{PERMISSION_SAY_HELLO},1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    boolean flag = false;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        L.e(requestCode + "--" + grantResults[0]);
    }

    @Override
    public void onClick(View v) {
//        flag = !flag;
//        if (flag) {
//        } else {
//            lv.resume();
//        }
//        startActivity(new Intent(this, WebActivity.class));
        Map<String, Object> data = new HashMap<>();
        data.put("date", new Date());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data",new Date());
            L.e(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getData() {
        Uri uri = getIntent().getData();
        if (uri != null) {
            // 完整的url信息
            String url = uri.toString();
            Log.i(TAG, "url:" + uri);

            // scheme部分
            String scheme = uri.getScheme();
            Log.i(TAG, "scheme:" + scheme);

            // host部分
            String host = uri.getHost();
            Log.i(TAG, "host:" + host);

            // port部分
            int port = uri.getPort();
            Log.i(TAG, "port:" + port);

            // 访问路劲
            String path = uri.getPath();
            Log.i(TAG, "path:" + path);

            List<String> pathSegments = uri.getPathSegments();

            // Query部分
            String query = uri.getQuery();
            Log.i(TAG, "query:" + query);

            //获取指定参数值
            String success = uri.getQueryParameter("success");
            Log.i(TAG, "success:" + success);
        }
    }

    class BackgroundEvaluator extends ArgbEvaluator {
        private final GradientDrawable drawable;

        public BackgroundEvaluator(GradientDrawable drawable) {
            this.drawable = drawable;
        }

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Object evaluate = super.evaluate(fraction, startValue, endValue);
            drawable.setColor((Integer) evaluate);
            return evaluate;
        }
    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }


}
