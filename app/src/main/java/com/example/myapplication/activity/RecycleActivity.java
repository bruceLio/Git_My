package com.example.myapplication.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SimpleRecycleViewAdapter;
import com.example.myapplication.util.L;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        EasyRecyclerView rv = findViewById(R.id.rv);
        SimpleRecycleViewAdapter adapter = new SimpleRecycleViewAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
//        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                L.e("onChange");
//            }
//
//            @Override
//            public void onItemRangeChanged(int positionStart, int itemCount) {
//                super.onItemRangeChanged(positionStart, itemCount);
//                L.e("onItemRangeChanged");
//
//            }
//
//            @Override
//            public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
//                super.onItemRangeChanged(positionStart, itemCount, payload);
//                L.e("onItemRangeChanged");
//
//            }
//
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                super.onItemRangeInserted(positionStart, itemCount);
//                L.e("onItemRangeInserted");
//
//            }
//
//            @Override
//            public void onItemRangeRemoved(int positionStart, int itemCount) {
//                super.onItemRangeRemoved(positionStart, itemCount);
//                L.e("onItemRangeRemoved");
//
//            }
//
//            @Override
//            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
//                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
//                L.e("onItemRangeMoved");
//
//            }
//        });
        rv.setAdapter(adapter);
        List<String> data = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            data.add("position" + i);
        }
        adapter.setData(data);
    }
}
