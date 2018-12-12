package com.example.myapplication.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.SingletonTest;
import com.example.myapplication.adapter.SimpleFragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends BaseActivity {
    private DrawerLayout drawer;

    public void initUI() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                drawer.openDrawer(GravityCompat.START);
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
         drawer = findViewById(R.id.drawer);
        initUI();
        ViewPager vp = findViewById(R.id.vp);
        TabLayout tab = findViewById(R.id.tab);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabTextColors(ContextCompat.getColor(this, R.color.gray),
                ContextCompat.getColor(this, R.color.white));
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            titles.add("position" + i);
        }
        vp.setAdapter(new SimpleFragmentViewPagerAdapter(getSupportFragmentManager(), titles));
        tab.setupWithViewPager(vp);
//        SingletonTest instance = SingletonTest.getInstance(this);
//        mHandler.sendMessage(new Message());



    }
//    private Handler mHandler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            },3000);
//        }
//    };
}
