package com.example.myapplication;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by xiaolong on 2018/6/8.
 */
public class NotificatinManager {


    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_MIUI = "sys_miui";
    public static final String SYS_FLYME = "sys_flyme";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";



    public static boolean goAppNotificationManagement(Activity mActivity) {
        L.e(getSystem());
        try {
            if (Build.VERSION.SDK_INT>=20) {
                Intent intent = new Intent();
                if(Build.VERSION.SDK_INT >=26){

                    intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
//                    Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
//                    intent.setData(uri);

//                    intent.setComponent(cm);
//                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                    intent.putExtra("android.provider.extra.APP_PACKAGE", mActivity.getPackageName());
                }else {
                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                    intent.putExtra("app_package", mActivity.getPackageName());
                    intent.putExtra("app_uid", mActivity.getApplicationInfo().uid);

                }
                mActivity.startActivity(intent);

                T.show(mActivity,"打开显示应用通知权限。");
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }


    public static String getSystem(){
        String SYS = null;
        try {
            Properties prop= new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if(prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null){
                SYS = SYS_MIUI;//小米
            }else if(prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    ||prop.getProperty(KEY_EMUI_VERSION, null) != null
                    ||prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null){
                SYS = SYS_EMUI;//华为
            }else if(getMeizuFlymeOSFlag().toLowerCase().contains("flyme")){
                SYS = SYS_FLYME;//魅族
            };
        } catch (IOException e){
            e.printStackTrace();
            return SYS;
        }
        return SYS;
    }
    public static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String)get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
        }
        return defaultValue;
    }
}
