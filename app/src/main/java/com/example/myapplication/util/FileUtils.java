package com.example.myapplication.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lixiaolong
 * @date 2018/11/8
 */
public class FileUtils {
    public static void storeToExternalFile(String path, String data) {
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(path);
            outStream.write(data.getBytes());
        } catch (Exception e) {
            L.e(e.getMessage());
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
