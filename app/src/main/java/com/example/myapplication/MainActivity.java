package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.activity.BaseActivity;
import com.example.myapplication.activity.TabActivity;
import com.example.myapplication.helper.LifecycleHelper;
import com.example.myapplication.util.L;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        getLifecycle().addObserver(new LifecycleHelper());
        gotoActivity(TabActivity.class);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.e("x=" + ev.getX() + ",y=" + ev.getY());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        gotoActivity(TabActivity.class);

    }
}
