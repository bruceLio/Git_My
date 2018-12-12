package com.example.myapplication;

import android.content.Context;

/**
 * @author lixiaolong
 * @date 2018/9/11
 */
public class SingletonTest {

    private static SingletonTest instance;

    public static SingletonTest getInstance(Context context) {
        if(instance ==null){
            instance=new SingletonTest(context);

        }
        return instance;
    }
    private SingletonTest(Context context){

    }
}
