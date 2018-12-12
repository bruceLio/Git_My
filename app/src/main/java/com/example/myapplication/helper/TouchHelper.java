package com.example.myapplication.helper;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.util.L;
import com.example.myapplication.util.ScreenUtils;

/**
 * @author lixiaolong
 * @date 2018/9/7
 */
public class TouchHelper {

    private final int maxHeight;
    private final int width;

    public TouchHelper(Activity activity) {
        width = ScreenUtils.getWidth(activity);
        maxHeight = ScreenUtils.getHeight(activity) - ScreenUtils.getStatusBarHeight(activity);
    }

    float downx = 0;
    float downY = 0;

    public void attachedToView(View v) {
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downx = event.getX();
                        downY = event.getY();
                        L.e("downX ="+downx+"  downY="+downY);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float dx = x - downx;
                        float dy = y - downY;
                        int l, r, t, b;
                        l = (int) (v.getLeft() + dx);
                        r = l + v.getWidth();
                        t = (int) (v.getTop() + dy);
                        b = t + v.getHeight();
                        v.layout(l, t, r, b);
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        L.e("123");
                        float dxUp = x - downx;
                        float dyUp = y - downY;
                        if (dxUp == 0 && dyUp == 0) {
                            L.e("mDownX ="+downx+"  mDownY="+downY+" eX="+event.getX()+" eY="+event.getY());
//                            return v.callOnClick();
                        } else {
                            if (v.getRight() > width / 2 + v.getWidth() / 2) {
                                v.layout(width - v.getWidth(), v.getTop(), width, v.getBottom());
                            } else {
                                v.layout(0, v.getTop(), v.getWidth(), v.getBottom());
                            }
                            if (v.getTop() < 0) {
                                v.layout(v.getLeft(), 0, v.getRight(), v.getHeight());
                            }
                            if (v.getBottom() > maxHeight) {
                                v.layout(v.getLeft(), maxHeight - v.getHeight(), v.getRight(), maxHeight);

                            }
                            return true;
                        }
                    default:
                        break;
                }

                return true;
            }
        });

    }
}
