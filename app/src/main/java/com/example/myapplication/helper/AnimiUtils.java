package com.example.myapplication.helper;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * Created by xiaolong on 2018/6/25.
 */
public class AnimiUtils {
    public static void startAnim(View v){
        AnimationSet set=new AnimationSet(true);
        TranslateAnimation translateAnimation=new TranslateAnimation( Animation.RELATIVE_TO_PARENT, 0.5f,  //X轴的开始位置
                Animation.RELATIVE_TO_PARENT, 0 , //X轴的结束位置
                Animation.RELATIVE_TO_PARENT, 0.5f,  //Y轴的开始位置
                Animation.RELATIVE_TO_PARENT, 0);  //Y轴的结束位置  );
        AlphaAnimation alphaAnimation=new AlphaAnimation(0, 1);
        set.addAnimation(alphaAnimation);
        set.addAnimation(translateAnimation);
        set.setDuration(1000);
        v.startAnimation(set);
    }
}
