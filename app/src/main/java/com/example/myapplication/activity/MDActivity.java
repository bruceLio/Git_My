package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SimpleAdapter;
import com.example.myapplication.adapter.SimpleRecycleViewAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MDActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);
        RecyclerView rv = findViewById(R.id.rv);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("position" + i);
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        SimpleAdapter adapter = new SimpleAdapter(this,list);
        rv.setAdapter(adapter);

    }
}
