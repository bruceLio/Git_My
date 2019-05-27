package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MusicTestAdapter;
import com.example.myapplication.bean.MusicTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaolong
 * @date 2019/4/26
 */
public class TestActivity extends BaseActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<MusicTest> data = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            data.add(new MusicTest());
        }
        RecyclerView.Adapter adapter = new MusicTestAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
}
