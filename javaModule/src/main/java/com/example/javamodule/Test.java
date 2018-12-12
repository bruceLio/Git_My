package com.example.javamodule;

import java.io.File;
import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import file.util.FileType;

public class Test {


    public static class SynchronizedTest {


        synchronized void t()

        {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getId() + "---" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {
        File file=new File("/Users/edz/Desktop/1.rar");
        FileType.decompress(file,file.getParent()+"/"+FileType.getFileName(file.getName()),false);

    }

}
