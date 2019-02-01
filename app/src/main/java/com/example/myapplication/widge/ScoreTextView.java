package com.example.myapplication.widge;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


/**
 * @author lixiaolong
 * @date 2019/1/10
 */
public class ScoreTextView extends TextView {
    public void setScore(int score) {
        this.score = score;
        setText(String.valueOf(score));
    }

    private int score;

    public ScoreTextView(Context context) {
        this(context, null);
    }

    public ScoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setText("0");
    }

    public void setScoreWithAnim(int score) {
        String text = getText().toString();
        if (TextUtils.isEmpty(text)) {
            text = "0";
        }
        try {
            int i = Integer.parseInt(text);
            if (i != score) {
                ObjectAnimator animator = ObjectAnimator.ofInt(this, "score", i, score);
                animator.setDuration(200);
                animator.start();
            }
        } catch (Exception e) {
            Log.e("ScoreTextView", "text must be integer");
        }
    }
}
