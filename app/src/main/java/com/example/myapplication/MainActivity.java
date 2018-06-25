package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.helper.AnimiUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimiUtils.startAnim(findViewById(R.id.v));
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.e("x="+ev.getX()+",y="+ev.getY());
        return super.dispatchTouchEvent(ev);
    }

}
