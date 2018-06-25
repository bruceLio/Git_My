package com.example.myapplication;

import android.content.Context;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by xiaolong on 2018/6/13.
 */
public class WxHelper {
    void test(Context context,String str){
//        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
//        miniProgramObj.webpageUrl = "http://www.qq.com"; // 兼容低版本的网页链接
//        miniProgramObj.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;// 正式版:0，测试版:1，体验版:2
//        miniProgramObj.userName = "gh_d43f693ca31f";     // 小程序原始id
//        miniProgramObj.path = "/pages/media";            //小程序页面路径
//        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
//        msg.title = "小程序消息Title";                    // 小程序消息title
//        msg.description = "小程序消息Desc";               // 小程序消息desc
//        msg.thumbData = getThumb();                      // 小程序消息封面图片，小于128k
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("webpage");
//        req.message = msg;
//        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前支持会话
//        api.sendReq(req);

//        AppID：wxc819e3156d7bd8d2
        String appId = "wxc819e3156d7bd8d2"; // 填应用AppId
        IWXAPI api = WXAPIFactory.createWXAPI(context, appId);
//        pages/index/index?uuid=030846EC&third_uid=xxxxx
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
//        req.userName = "gh_52716daea2fe"; // 填小程序原始id
        req.userName = "gh_c4acbbec48ca"; // 填小程序原始id
        req.path = "pages/index/index?uuid=030846EC&third_uid="+str;                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
        api.sendReq(req);
    }
}
