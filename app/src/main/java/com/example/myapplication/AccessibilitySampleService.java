package com.example.myapplication;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

import com.example.myapplication.util.L;

/**
 * @author lixiaolong
 * @date 2019-05-22
 */
public class AccessibilitySampleService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        L.e("onAccessibilityEvent");
    }

    @Override
    public void onInterrupt() {
        L.e("onInterrupt");
    }
}
