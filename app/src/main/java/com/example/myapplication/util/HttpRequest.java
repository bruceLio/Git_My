package com.example.myapplication.util;

import com.example.myapplication.jiami.CipherFastJsonHttpMessageConverter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author lixiaolong
 * @date 2018/9/11
 */
public class HttpRequest {
    public static String conver2HexStr(byte [] b)
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0;i<b.length;i++)
        {
            result.append(Long.toString(b[i] & 0xff, 2)+",");
        }
        return result.toString().substring(0, result.length()-1);}
    public static final String JSON="application/json; charset=utf-8";
    public static final String JSON_JIAMI="application/encrypted-json; charset=utf-8";
    public static final String URL="http://211.95.56.10:9780/frontend/order/list?userId=211390819&loginId=002413479f6a4e60966b43021905add8";
    public HttpRequest() {

        String abc = "{\"appVersion\":44,\"loginId\":\"002413479f6a4e60966b43021905add8\",\"userId\":211390819}";
        RequestBody body = RequestBody.create(MediaType.parse(JSON), new CipherFastJsonHttpMessageConverter().writeInternal(abc));
//        RequestBody body = RequestBody.create(MediaType.parse(JSON), abc.getBytes());
        Request req = new Request.Builder()
                .post(body)
//                .addHeader("Accept","application/encrypted-json")
                .url(URL).build();
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null) {
                    String string = response.body().string();
                    L.e(string);
                }
            }
        };
        new OkHttpClient().newCall(req).enqueue(callback);

    }

}
