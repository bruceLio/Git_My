package com.example.myapplication.util;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查权限的工具类
 */
public class PermissionsChecker {

    // 判断权限集合
    public static boolean lacksPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT <= 22) {
            return false;
        }
        //Log.i("jiawen", "PermissionsChecker lacksPermissions permission:" + permissions);
        for (String permission : permissions) {
            //Log.i("jiawen", "PermissionsChecker lacksPermissions permission:" + permission);
            if (lacksPermission(context, permission)) {
                return true;
            }
        }

        //Log.i("jiawen", "PermissionsChecker lacksPermissions return false");
        return false;
    }


    // 得到权限集合
    public static List<String> getLackPermissions(Context context, String... permissions) {

        List<String> listpermissions = new ArrayList<String>();

        for (String permission : permissions) {
            //LogUtil.i("zl","PermissionsChecker getLackPermissions permission:"+permission);
            if (lacksPermission(context, permission)) {

                listpermissions.add(permission);
            }
        }

        //LogUtil.i("zl","PermissionsChecker getLackPermissions return false");

        return listpermissions;

    }

    // 判断是否缺少权限
    private static boolean lacksPermission(Context context, String permission) {

        boolean lack = ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_DENIED;

        if (!lack) {
            //适配机型
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                String p = null;
                switch (permission){
                    case Manifest.permission.READ_PHONE_STATE:
                        p = AppOpsManager.OPSTR_READ_PHONE_STATE;
                        break;
                    case Manifest.permission.READ_CONTACTS:
                        p = AppOpsManager.OPSTR_READ_CONTACTS;
                        break;
                }

                if (p==null)return lack;

                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                int checkOp = appOpsManager.checkOp(p,android.os.Process.myUid(), context.getPackageName());
                lack = checkOp == AppOpsManager.MODE_IGNORED;
            }
        }
        return lack;
    }
}