package com.example.myapplication.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.example.myapplication.activity.PermissionActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * @author lixiaolong
 * @date 2019/2/25
 */
public class PermissionManager {
    private final Activity mContext;
    private String[] mNormalPermissions = {
            WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION, READ_PHONE_STATE};
    private ArrayList<String> mCheckPermissions;
    private PermissionCallback mCallback;

    public PermissionManager(Activity context) {
        mContext = context;
    }

    public void setCheckPermission(ArrayList<String> checkPermissions) {
        this.mCheckPermissions = checkPermissions;
    }


    public boolean checkPermission(String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(mContext, permission);
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    /**
     * 检查多个权限
     *
     * @param callback
     */
    public void checkMutiPermission(PermissionCallback callback) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (callback != null)
                callback.onFinish();
            return;
        }
        mCallback = callback;
        if (mCheckPermissions == null) {
            mCheckPermissions = new ArrayList<>();
            mCheckPermissions.addAll(Arrays.asList(mNormalPermissions));
        }
        //检查权限，过滤已允许的权限
        Iterator<String> iterator = mCheckPermissions.listIterator();
        while (iterator.hasNext()) {
            if (checkPermission(iterator.next()))
                iterator.remove();
        }
        if (mCheckPermissions.size() > 0) {
            startActivity();
        } else {
            if (callback != null)
                callback.onFinish();
        }


    }

    /**
     * 检查单个权限
     *
     * @param permission
     * @param callback
     */
    public void checkSinglePermission(String permission, PermissionCallback callback) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkPermission(permission)) {
            if (callback != null)
                callback.onGuarantee(permission, 0);
            return;
        }
        mCallback = callback;
        mCheckPermissions = new ArrayList<>();
        mCheckPermissions.add(permission);
        startActivity();
    }

    public interface PermissionCallback extends Serializable {

        void onFinish();

        void onDeny(String permission, int position);

        void onGuarantee(String permission, int position);

        void onClose();
    }

    private void startActivity() {
//        mContext.requestPermissions();
        Intent intent = new Intent();
        intent.putStringArrayListExtra("permissions", mCheckPermissions);
        intent.setClassName(mContext, PermissionActivity.class.getName());
        PermissionActivity.setCallBack(mCallback);
        mContext.startActivity(intent);
    }


}
