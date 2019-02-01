package com.example.myapplication;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.Vibrator;
import android.support.v4.content.PermissionChecker;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.activity.BaseActivity;
import com.example.myapplication.activity.ChannelActivity;
import com.example.myapplication.util.L;
import com.example.myapplication.util.OSHelper;
import com.example.myapplication.util.SharedPreferencesUtils;
import com.example.myapplication.widge.GradientTextView;
import com.example.myapplication.widge.NetView;
import com.example.myapplication.widge.ScoreTextView;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private AnimatorSet btnAnimSet;
    private ScoreTextView tvScore;
    int score = 0;
    private CountDownTimer timer;
    private Button bt;
    private View my;
    private TextView tvTimeMy;
    private Handler mHandler;
    private BroadcastReceiver receiver;
    GradientTextView gradientTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = findViewById(R.id.tv_score);
        bt = findViewById(R.id.button);
        my = findViewById(R.id.rl_top_my);
        tvTimeMy = findViewById(R.id.tv_time);
        findViewById(R.id.button).setOnClickListener(this);
        startService(new Intent(this, LockService.class));
        gradientTextView = findViewById(R.id.gt);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        gradientTextView.setText(getResources().getString(R.string.arrow_right));
        gradientTextView.setTypeface(iconfont);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ff16b2ff"));
        }
        View viewById = findViewById(R.id.tv_mid);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gradientTextView.cancle();
    }

    boolean a = true;

    @Override
    public void onClick(View v) {
        gotoActivity(ChannelActivity.class);
    }

    class BackgroundEvaluator extends ArgbEvaluator {


        private final GradientDrawable drawable;

        public BackgroundEvaluator(GradientDrawable drawable) {
            this.drawable = drawable;
        }

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Object evaluate = super.evaluate(fraction, startValue, endValue);
            drawable.setColor((Integer) evaluate);
            return evaluate;
        }
    }

}
