package com.example.myapplication.widge;

import android.os.IBinder;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author lixiaolong
 * @date 2018/9/25
 */
public class ToastPop  extends PopupWindow{


    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);

    }
    private void hide(){

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
