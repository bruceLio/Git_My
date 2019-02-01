package com.example.myapplication.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.myapplication.R;


/**
 * Created by xiaolong on 2018/6/21.
 */
public class AnimUtils {

    private static void startBonusInAnim(final View bonusContainer, long offset) {
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


    public static void startHongBaoRotate(final ImageView hongbaoView) {
        ObjectAnimator hongbaoRotate = ObjectAnimator.ofFloat(hongbaoView, "rotation", 0, -15, 0, 15, 0);
        hongbaoRotate.setDuration(200);
        hongbaoRotate.setRepeatCount(1);
        hongbaoRotate.setInterpolator(new DecelerateInterpolator());
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
        if (hongbaoView == null || coinView == null) return;
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
        startCoinRotate(hongbaoView, coinView);
        startBonusInAnim(bonusContainer, hongbaoView == null ? 0 : 400);

    }

    public static void showNewAnim(View hongbao) {
//        Animation animation = AnimationUtils.loadAnimation(TestApplication.app, R.anim.anim_out);
//        animation.setDuration(2000);
//        hongbao.startAnimation(animation);
//        TranslateAnimation anim=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0,
//                Animation.RELATIVE_TO_PARENT,0,
//                Animation.RELATIVE_TO_PARENT,0,
//                Animation.RELATIVE_TO_PARENT,1f);
//        anim.setInterpolator(new DecelerateInterpolator());
//        anim.setDuration(2000);
//        hongbao.startAnimation(anim);
        ObjectAnimator animator = ObjectAnimator.ofFloat(hongbao, "translationY", 0, 500f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(1000);
        animator.start();

    }

    public static void showBonusAnim(View bonus) {

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(bonus, "scaleX", 0, 1);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(bonus, "scaleY", 0, 1);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000).play(scaleX).with(scaleY);
        set.start();
//        ScaleAnimation animation=new ScaleAnimation(0,1,0,1,bonus.getWidth()/2,bonus.getHeight()/2);
//        animation.setInterpolator(new DecelerateInterpolator());
//        animation.setDuration(2000);
//        bonus.startAnimation(animation);

    }

    public static void showAnim(View hongbao, View bonus) {
        bonus.setVisibility(View.VISIBLE);
        bonus.setScaleX(0);
        bonus.setScaleY(0);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(hongbao, "translationY", 0, 300f);
        translationY.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(hongbao, "translationY", 300f, 0);
        translationY.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(bonus, "scaleX", 0, 1);
        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(bonus, "scaleX", 1, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(bonus, "scaleY", 0, 1);
        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(bonus, "scaleY", 1, 0);
        AnimatorSet in = new AnimatorSet();
        in.setDuration(800).play(scaleX).with(scaleY).with(translationY);
        AnimatorSet out = new AnimatorSet();
        out.setDuration(800).play(scaleX2).with(scaleY2).with(translationY2).after(3000);
        AnimatorSet all = new AnimatorSet();
        all.play(in).before(out);
        all.start();
    }

    public static void showRoundAnim(Context context, final View a, final View b) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.exercise_class);
        a.startAnimation(animation);
        b.startAnimation(AnimationUtils.loadAnimation(context, R.anim.exercise_round));
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        ObjectAnimator aInAnim = ObjectAnimator.ofFloat(a, "translationY", -100, 0);
//        ObjectAnimator aOutAnim1 = ObjectAnimator.ofFloat(a, "ScaleX", 1.0f, 0.5f);
//        ObjectAnimator aOutAnim2 = ObjectAnimator.ofFloat(a, "ScaleY", 1.0f, 0.5f);


    }
}
