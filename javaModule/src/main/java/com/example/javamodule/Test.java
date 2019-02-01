package com.example.javamodule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import file.util.FileType;

public class Test {


    public static void main(String[] args) {
        List<Integer>list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        move(list,9,0);
        move(list,9,1);
        System.err.println(list);
        move(list,9,2);
        move(list,9,3);
        move(list,9,4);
        move(list,9,5);
        System.err.println(list);
    }

    private static void move(List list, int from, int to) {
        if (from < to) {
            list.add(to, list.get(from));
            list.remove(from);
        } else {
            list.add(to, list.get(from));
            list.remove(from + 1);
        }
    }

    private static String int2C(int round) {
        String[] num = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String uni = "十";
//        if (round == 10) {
//            return uni;
//        }
        if (round < 10 && round >= 0) {
            return num[round];
        }
        if (round >= 10 && round < 100) {
            int ten = round / 10;
            int o = round % 10;
            String tenStr = num[ten] + uni;
            String oStr = num[o];
            if (ten == 1) {
                tenStr = uni;
            }
            if (o == 0) {
                oStr = "";
            }
            return tenStr + oStr;
        } else {
            return "";
        }

    }

}
