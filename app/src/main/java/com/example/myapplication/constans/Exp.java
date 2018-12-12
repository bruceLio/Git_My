package com.example.myapplication.constans;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * @author lixiaolong
 * @date 2018/9/7
 */
@IntDef({Exp.ONE, Exp.TWO, Exp.THREE})
@Retention(RetentionPolicy.SOURCE)
public @interface Exp {
    int ONE = 1;
    int TWO = 2;
    int THREE = 3;
}
