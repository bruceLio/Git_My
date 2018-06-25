package com.example.myapplication.util;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.myapplication.R;


/**
 * Created by xiaolong on 2018/6/21.
 */
public class AnimUtils {

    private static void startBonusInAnim(final View bonusContainer,long offset) {
        bonusContainer.setVisibility(View.VISIBLE);
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, .5f,  //X轴的开始位置
                Animation.RELATIVE_TO_PARENT, 0,  //X轴的结束位置
                Animation.RELATIVE_TO_PARENT, .5f,  //Y轴的开始位置
                Animation.RELATIVE_TO_PARENT, 0);  //Y轴的结束位置  );
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        set.addAnimation(alphaAnimation);
        set.addAnimation(translateAnimation);
        set.setStartOffset(offset);
        set.setDuration(600);
        bonusContainer.startAnimation(set);
    }



    private static void startBonusInnerAnim(final ImageView hongbaoView, View coinView) {
        if(hongbaoView==null||coinView==null)return;
        MyYAnimation yAnimation = new MyYAnimation();
        yAnimation.setDuration(200);
        yAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                hongbaoView.setImageResource(R.drawable.ico_hongbao);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        coinView.startAnimation(yAnimation);
        RotateAnimation rotateAnimation=new RotateAnimation(-15,15);
        rotateAnimation.setStartOffset(200);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                hongbaoView.setImageResource(R.drawable.ico_hongbao_dakai);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        hongbaoView.startAnimation(rotateAnimation);
    }

    public static void showBonusAnim(final View bonusContainer, ImageView hongbaoView, View coinView) {
        startBonusInnerAnim(hongbaoView,coinView);
        startBonusInAnim(bonusContainer,hongbaoView==null?0:400);

    }
}
