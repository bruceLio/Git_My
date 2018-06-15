package com.example.myapplication.helper;

import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiaolong on 2018/6/15.
 */
public class WebViewHelper {

    public static Map <String,Object>getDefaultMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("setAllowContentAccess",false);
        map.put("setJavaScriptEnabled",true);
        return map;

    }

    public static void bindSetting(WebView wv, ISetting setting) {
            if(setting==null)return;
        Map<String, Object> map = setting.getSettingMap();
        Class settingClass = null;
        try {
            settingClass = Class.forName("android.webkit.WebSettings");
            for (String key : map.keySet()) {
                Method method = settingClass.getMethod(key);
                method.invoke(wv.getSettings(),map.get(key));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
