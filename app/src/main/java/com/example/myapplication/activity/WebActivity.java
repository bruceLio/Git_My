package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.myapplication.R;

public class WebActivity extends BaseActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wv = findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.battlenet.com.cn/zh/");
        setWebViewSettings(wv);
    }

    private Object getHtmlObject() {
        Object object = new Object() {
            @JavascriptInterface
            public void showToast(String t) {
                Toast.makeText(WebActivity.this, t, Toast.LENGTH_SHORT).show();
            }
        };
        return object;
    }

    private void setWebViewSettings(WebView webView) {
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 12);
        try {
            String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
            webView.getSettings().setAppCachePath(appCachePath);
        } catch (Exception e) {
        }
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setUseWideViewPort(getIntent().getBooleanExtra("isSupportZoom", true));
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setSupportZoom(getIntent().getBooleanExtra("isSupportZoom", true));
            webView.getSettings().setBuiltInZoomControls(true);
        }
        webView.getSettings().setTextZoom(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
        webView.setWebChromeClient(new CustomWebChromeClient());
        webView.setWebViewClient(new CustomWebViewClient());
    }
}
