package com.example.myapplication;

import android.arch.lifecycle.LifecycleObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.helper.LifecycleHelper;
import com.example.myapplication.helper.WxHelper;
import com.example.myapplication.util.AnimUtils;
import com.example.myapplication.util.DevicesUtis;
import com.example.myapplication.util.L;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private View ivHongBao;
    private View tvBonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivHongBao = findViewById(R.id.iv_hongbao);
        tvBonus = findViewById(R.id.tv_bonus);
        findViewById(R.id.button).setOnClickListener(this);
        getLifecycle().addObserver(new LifecycleHelper());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.e("x=" + ev.getX() + ",y=" + ev.getY());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        if(TextUtils.isEmpty(DevicesUtis.getDeviceId(getApplication()))){
            DevicesUtis.gotoPermissionSetting(getApplication());
        }
    }
}
