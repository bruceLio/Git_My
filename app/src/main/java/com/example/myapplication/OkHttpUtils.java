package com.example.myapplication;

import java.io.IOException;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Http2Stream;

/**
 * Created by xiaolong on 2018/6/11.
 */
public class OkHttpUtils {
    private static Interceptor miter=new MyInterceptor();
   public static final MediaType JSON_CIPHER
            = MediaType.parse("application/encrypted-json; charset=utf-8");
   public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient cipherClient=  new OkHttpClient.Builder().addNetworkInterceptor(miter).build();
    public static OkHttpClient getCipherClient(){
        return  cipherClient;
    }
    public static void postCipher(Callback callback, Object body, String url){
        byte[] base64 = new CipherFastJsonHttpMessageConverter().getBase64(body);
        String s = new String(base64);
        RequestBody requestBody = RequestBody.create(JSON, base64);
//        RequestBody.
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = getCipherClient().newCall(request);

        call.enqueue(callback);

    }


    private static class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request= new Request.Builder()
                    .url(request.url())
                    .method(request.method(),request.body())
                    .headers(request.headers())
                    .header("Content-Type","application/encrypted-json")
                    .header("Accept","application/encrypted-json")
                    .build();
            Response response = chain.proceed(request);
//            ResponseBody body=response.body();

            L.e("123");
            return response;
        }
    }

}
