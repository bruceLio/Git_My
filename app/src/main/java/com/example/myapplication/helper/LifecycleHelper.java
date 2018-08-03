package com.example.myapplication.helper;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.myapplication.util.L;

public class LifecycleHelper implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void testOnPause() {
        L.e("this is onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void testOnResume() {
        L.e("this is onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void test() {
        L.e("this is  test onCreate");
    }

}
