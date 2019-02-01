package com.example.myapplication.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

/**
 * @author lixiaolong
 * @date 2018/12/24
 */
public class AnimView extends RelativeLayout{




    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void addChild(){
        ImageView imageView =new ImageView(getContext());
        imageView.setBackgroundResource(R.drawable.ico_jinbi);
    }

}
