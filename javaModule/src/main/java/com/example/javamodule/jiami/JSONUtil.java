package com.example.javamodule.jiami;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

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
