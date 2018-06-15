package com.example.myapplication;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaolong on 2018/6/7.
 */
public class TouchHelper implements View.OnTouchListener {
    public void setOnTouch(TouchInterface mTouchInterface) {
        this.mTouchInterface = mTouchInterface;
    }


    private TouchInterface mTouchInterface=new TouchInterface() {
        @Override
        public void onDown(int x, int y) {
            L.e("down x=="+x+"  y=="+y);
        }

        @Override
        public void onUp(int x, int y) {
            L.e("up x=="+x+"  y=="+y);
        }
    };
//    public TouchHelper(Activity context) {
//        View view = ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
//
//        view.setOnTouchListener(this);
//
//    }

    public TouchHelper bindActivity(Activity context){
        View view = ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);

        view.setOnTouchListener(this);

        return this;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            /**
             * 点击的开始位置
             */
            case MotionEvent.ACTION_DOWN:
                if(mTouchInterface!=null)mTouchInterface.onDown((int)event.getX(),(int)event.getY());
                break;
            /**
             * 触屏实时位置
             */
            case MotionEvent.ACTION_MOVE:
                break;
            /**
             * 离开屏幕的位置
             */
            case MotionEvent.ACTION_UP:
                if(mTouchInterface!=null)mTouchInterface.onUp((int)event.getX(),(int)event.getY());
                break;
            default:
                break;
        }
        /**
         *  注意返回值
         *  true：view继续响应Touch操作；
         *  false：view不再响应Touch操作，故此处若为false，只能显示起始位置，不能显示实时位置和结束位置
         */
        return true;
    }

}
