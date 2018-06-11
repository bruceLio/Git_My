package com.example.javamodule;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiaolong on 2018/6/8.
 */
public class Request {

    private final InputStream inputStream;

    public Request(InputStream inputStream){
        this.inputStream=inputStream;
    }
    public void parse(){
        StringBuffer stringBuffer=new StringBuffer(2048);
        int i=0;
        byte []buffer=new byte[2048];
        try {
            i=inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int j = 0; j < i; j++) {
            stringBuffer.append((char) buffer[j]);
        }
        System.err.println(stringBuffer.toString());
    }
    private void parseUri(String uri){

    }
}
