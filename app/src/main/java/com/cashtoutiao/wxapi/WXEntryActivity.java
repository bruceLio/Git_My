package com.cashtoutiao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.util.L;
import com.example.myapplication.util.WxUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        initData();
    }

    private void initData() {
//        L.e("1234");
        api = WxUtils.getApi();
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        L.e("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e("onresume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.e("onstop");
    }

    @Override
    public void onResp(BaseResp resp) {
        L.e("分享"+resp.errCode);
        String result = null;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "分享成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "分享取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "分享被拒绝";
                break;
            default:
                result = "分享返回";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        this.finish();
    }
}