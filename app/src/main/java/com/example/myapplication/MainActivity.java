package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements TouchInterface {

    final String url = "http://211.95.56.10:9780/frontend/news/channel?userId=211390830&loginId=b570b5f0895d4583b81bdad320ccab8c";
    final String url2 = "http://211.95.56.10:9780/frontend/daily/task/list?userId=211390830&loginId=b570b5f0895d4583b81bdad320ccab8c";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final HashMap<String, String> map = new HashMap<>();
        map.put("loginId","b570b5f0895d4583b81bdad320ccab8c");
        map.put("userId" ,"211390830");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String json = "{\"appVersion\":100,\"loginId\":\"b570b5f0895d4583b81bdad320ccab8c\",\"userId\":211390830}";
//                RequestBody body = RequestBody.create(JSON, json);
//                final Request request = new Request.Builder()
//                        .url(url)
//                        .post(body)
//                        .build();

                Callback callback=new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        L.e(string);
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        outputStream.write(string.getBytes());

                        String cipher = new CipherFastJsonHttpMessageConverter().readCipher(String.class,string);
                        L.e(cipher);


                    }
                };
                OkHttpUtils.postCipher(callback,map, url2);

            }
        });
    }


    @Override
    public void onDown(int x, int y) {

    }

    @Override
    public void onUp(int x, int y) {

    }
}
