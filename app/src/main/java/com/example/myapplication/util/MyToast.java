package com.example.myapplication.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

/**
 * @author lixiaolong
 * @date 2019/3/14
 */
public class MyToast extends Toast {
    private final Context mContext;
    private TextView tv;
    private static MyToast myToast;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public MyToast(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public static MyToast getMyToast(Context context) {
        if (myToast == null) {
            myToast = new MyToast(context);
        }
        return myToast;
    }

    private void initView() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_toast, null);
        tv = inflate.findViewById(R.id.tv);
        setGravity(Gravity.CENTER,0,0);
        setView(inflate);
    }

    public static void showShort(Context context, String msg) {
                getMyToast(context).tv.setText(msg);
                getMyToast(context).show();
    }

}
