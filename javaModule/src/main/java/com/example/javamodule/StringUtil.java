package com.example.javamodule;

/**
 * Created by xiaolong on 2018/6/8.
 */
public class StringUtil {


    //a字符串中出现b的次数及位置
    public int getFirstPosition(String str,String target){
        return str.indexOf(target);
    }

    public int getTargetPosition(String str,String target,int count){
        char[] chars = str.toCharArray();
        int firstPosition = getFirstPosition(str, target);
        String otherStr = str.substring(firstPosition + 1, str.length() - 1);
        int secondePosition=getFirstPosition(otherStr,target);
        return secondePosition;
    }


}
