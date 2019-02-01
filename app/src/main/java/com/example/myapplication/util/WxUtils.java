package com.example.myapplication.util;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.TestApplication;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author lixiaolong
 * @date 2019/1/2
 */
public class WxUtils {
    private static final String APP_ID = "wxc819e3156d7bd8d2";

    private static int THUMB_SIZE = 150;

    public static void shareImg() {
        Bitmap bmp = BitmapFactory.decodeResource(TestApplication.app.getResources(), R.drawable.heart);

//初始化 WXImageObject 和 WXMediaMessage 对象
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

//设置缩略图
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

//构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
//调用api接口，发送数据到微信
        getApi().sendReq(req);
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private static IWXAPI getApi() {
        return WXAPIFactory.createWXAPI(TestApplication.app, APP_ID);
    }

    public static void startWx(Context context) {
//        Intent shareIntent = new Intent();
//
//        //发送图片给好友。
//        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
//        shareIntent.setComponent(comp);
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_STREAM, getUriFromDrawableRes(TestApplication.app, R.drawable.heart));
//        shareIntent.setType("image/*");
//        context.startActivity(Intent.createChooser(shareIntent, "分享图片"));

        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        imageUris.add(getUriFromDrawableRes(TestApplication.app, R.drawable.heart));
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        context.startActivity(intent);
    }

    public static Uri getUriFromDrawableRes(Context context, int id) {
        Resources resources = context.getResources();
        String path = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(id) + "/"
                + resources.getResourceTypeName(id) + "/"
                + resources.getResourceEntryName(id);
        return Uri.parse(path);
    }

    public static void startWxIntent(Context context) {
        Bitmap bmp = BitmapFactory.decodeResource(TestApplication.app.getResources(), R.drawable.heart);

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        Intent intent = new Intent();
        intent.setClassName("com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity");
        intent.putExtra("_mmessage_sdkVersion", 620824064);
        intent.putExtra("_mmessage_appPackage", "com.cashtoutiao");
        intent.putExtra("imageData", Util.bmpToByteArray(thumbBmp, true));
        context.startActivity(intent);
    }

    public static void openWx(Context context) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
    }

    public static void saveBmp2Gallery(Bitmap bmp, String picName, Context mContext) {
        String fileName = null;
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
        File file = null;
        FileOutputStream outStream = null;

        try {
            file = new File(galleryPath, picName + ".jpg");
            fileName = file.getPath();
            outStream = new FileOutputStream(fileName);
            Bitmap.CompressFormat bmp_format = Bitmap.CompressFormat.JPEG;
            bmp.compress(bmp_format, 90, outStream);

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        mContext.sendBroadcast(intent);
        Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT).show();
    }

    public static int v() {
        return getApi().getWXAppSupportAPI();
    }
}
