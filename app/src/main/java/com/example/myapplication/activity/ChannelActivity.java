package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ChannelAdapter;
import com.example.myapplication.adapter.SimpleAdapter;
import com.example.myapplication.util.MyItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaolong
 * @date 2018/12/13
 */
public class ChannelActivity extends BaseActivity {
    TextView tv;
    RecyclerView rvTop;
    RecyclerView rvBottom;
    private SimpleAdapter bottomAdapter;
    private ChannelAdapter topAdapter;
    private boolean topBuzy = false;
    private boolean bottomBuzy = false;
    List<String> topData = new ArrayList<>();
    List<String> bottomData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        tv = findViewById(R.id.tv);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        GridLayoutManager manager2 = new GridLayoutManager(this, 3);
        rvTop = findViewById(R.id.rv_top);
        rvBottom = findViewById(R.id.rv_bottom);


        for (int i = 0; i < 18; i++) {
            topData.add("top" + i);
            bottomData.add("bottom" + i);
        }
        topAdapter = new ChannelAdapter(this, topData);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(topAdapter));
        itemTouchHelper.attachToRecyclerView(rvTop);
        bottomAdapter = new SimpleAdapter(this, bottomData);
        rvTop.setLayoutManager(manager);
        rvBottom.setLayoutManager(manager2);
        rvTop.setAdapter(topAdapter);
        rvBottom.setAdapter(bottomAdapter);
//        topAdapter.setItemClickListener(new SimpleAdapter.OnItemClick() {
//            @Override
//            public void onClick(String str, final View view) {
//                if (topAdapter.removeData(topData.indexOf(str))) {
//                    startTvAnim(str, view, false);
//                    bottomAdapter.addData(0, str);
//                }
//            }
//        });
//        bottomAdapter.setItemClickListener(new SimpleAdapter.OnItemClick() {
//            @Override
//            public void onClick(String str, View view) {
////                startTvAnim(str, view, true);
//                if (bottomAdapter.removeData(bottomData.indexOf(str))) {
//                    topAdapter.addData(topData.size(), str);
//                }
//            }
//        });
    }

    private void startTvAnim(String str, final View view, boolean up) {
        int width = view.getWidth();
        int height = view.getHeight();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        params.width = width;
        params.height = height;
        if (up) {
            params.topMargin = view.getTop() + rvBottom.getTop();

        } else {
            params.topMargin = view.getTop();
        }
        params.leftMargin = view.getLeft();
        tv.setText(str);
        tv.setLayoutParams(params);
        int y = rvBottom.getTop() - rvTop.getTop() - view.getTop();
        if (up) {
            y = -(view.getTop() + rvBottom.getTop() - rvTop.getHeight());
        }
        int x = view.getLeft();
        TranslateAnimation animation = new TranslateAnimation(0, -x, 0, y);
        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tv.startAnimation(animation);
    }
}
