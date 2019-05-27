package com.example.myapplication.widge;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.myapplication.R;
import com.example.myapplication.util.L;

/**
 * @author lixiaolong
 * @date 2019/4/12
 */
public class TestView extends FrameLayout {


    public TestView(@NonNull Context context) {
        super(context);
        init();

    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        inflate(getContext(), R.layout.layout_test, this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        L.e(w + "--" + h + "--" + oldw + "--" + oldh);
    }
}
