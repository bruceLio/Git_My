package com.example.javamodule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {


    public static void main(String[] args) {
        Map<String ,Object> data =new HashMap<>();
        data.put("date",new Date());
    }

    private static <T> List<T> getRandomList(List<T> data, int count) {
        List<T> result = new ArrayList<>();
        int size = data.size();
        if (size >= count) {
            List<T> copy = data;
            Collections.shuffle(copy);
            result.addAll(copy.subList(0, count));
        } else {
            while (count > size) {
                count -= size;
                result.addAll(getRandomList(data, size));
            }
            result.addAll(getRandomList(data, count));
        }
        return result;
    }

    private static int getd(int from, int target) {
        return target - from > 0 ? target - from : target - from + 10;
    }

    private static double getPercent(int currentNum, int mTarget, int mDeltaNum) {
        int progress = mTarget - currentNum;
        if (progress < 0) {
            progress = progress + 10;
        }
        return 1 - progress * 1.0 / mDeltaNum;
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
