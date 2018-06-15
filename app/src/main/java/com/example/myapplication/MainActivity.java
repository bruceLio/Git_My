package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.myapplication.helper.ISetting;
import com.example.myapplication.helper.WebViewHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Response;

import static android.provider.Settings.ACTION_BATTERY_SAVER_SETTINGS;

public class MainActivity extends AppCompatActivity implements TouchInterface {

    final String url = "http://211.95.56.10:9780/frontend/news/channel?userId=211390830&loginId=b570b5f0895d4583b81bdad320ccab8c";
    final String url2 = "http://211.95.56.10:9780/frontend/daily/task/list?userId=211390830&loginId=b570b5f0895d4583b81bdad320ccab8c";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

public static final String APP_ID="wxc819e3156d7bd8d2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView webView = new WebView(this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewHelper.bindSetting(webView, new ISetting() {
                    @Override
                    public Map<String, Object> getSettingMap() {
                        return WebViewHelper.getDefaultMap();
                    }
                });

                WebSettings settings = webView.getSettings();
                L.e("JavaScriptEnabled()=="+settings.getJavaScriptEnabled());
                L.e("getAllowContentAccess=="+settings.getAllowContentAccess());
            }
        });
    }

    private void http() throws IOException {
        final HashMap<String, String> map = new HashMap<>();
        map.put("loginId", "b570b5f0895d4583b81bdad320ccab8c");
        map.put("userId", "211390830");
        map.put("appVersion", "100");
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                L.e(string);
                String s = Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write(string.getBytes());
                InputStream inputStream = response.body().byteStream();
                String cipher = new CipherFastJsonHttpMessageConverter().readJsonStr(String.class, inputStream);
//                        String cipher = new CipherFastJsonHttpMessageConverter().readCipher(String.class,string);
                L.e(cipher);


            }
        };
        OkHttpUtils.postCipher(callback, map, url2);
    }


    @Override
    public void onDown(int x, int y) {

    }

    @Override
    public void onUp(int x, int y) {

    }
}
