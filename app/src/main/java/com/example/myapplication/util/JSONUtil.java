package com.example.myapplication.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JSONUtil {

	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	public static <T> T parseObject(String text, Class<T> cls) {
		return JSON.parseObject(text, cls);
	}

    public static <T> List<T> parseArray(String text, Class<T> cls) {
        return JSON.parseArray(text, cls);
    }

	public static Map<String,String> parseMap(String json){
		return JSON.parseObject(json, Map.class);
	}
}
