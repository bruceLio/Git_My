package com.example.javamodule.jiami;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaolong on 2018/6/11.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String encriptData="{\"appVersion\":100,\"loginId\":\"b570b5f0895d4583b81bdad320ccab8c\",\"userId\":211390830}";
        Map<String, Object> paramData=new HashMap<>();
        paramData.put("loginId","b570b5f0895d4583b81bdad320ccab8c");
        paramData.put("userId","211390830");
        OutputStream outSt=new BufferedOutputStream(System.out);
        new  CipherFastJsonHttpMessageConverter().writeInternal(paramData,outSt);
        outSt.write(encriptData.getBytes());
        outSt.flush();

    }
}
