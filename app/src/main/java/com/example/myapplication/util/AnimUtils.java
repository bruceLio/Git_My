package com.example.myapplication.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
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


    public static void startHongBaoRotate(final ImageView hongbaoView){
        ObjectAnimator hongbaoRotate=ObjectAnimator.ofFloat(hongbaoView,"rotation",0,-15,0,15,0);
        hongbaoRotate.setDuration(200);
        hongbaoRotate.setRepeatCount(1);
        hongbaoRotate.setInterpolator( new DecelerateInterpolator());
        hongbaoRotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                hongbaoView.setImageResource(R.drawable.ico_hongbao);
            }
        });
        hongbaoRotate.start();

    }

    public static void startCoinRotate(final ImageView hongbaoView, View coinView) {
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
                hongbaoView.setImageResource(R.drawable.ico_hongbao_dakai);
                startHongBaoRotate(hongbaoView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        coinView.startAnimation(yAnimation);
    }

    public static void showBonusAnim(final View bonusContainer, ImageView hongbaoView, View coinView) {
        startCoinRotate(hongbaoView,coinView);
        startBonusInAnim(bonusContainer,hongbaoView==null?0:400);

    }
}
