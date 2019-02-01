package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.myapplication.R;

public class WebActivity extends BaseActivity {

    private WebView wv;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wv = findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(getHtmlObject(), "t");
        wv.loadUrl("file:///android_asset/t.html");
        wv.loadUrl("javascript:jsAlert()");
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
}
