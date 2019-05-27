package com.example.myapplication.hotfix;

import java.lang.reflect.Method;

/**
 * @author lixiaolong
 * @date 2018/12/11
 */
public class HotFixManager {

    private static long measureMethodSize(Class c) throws NoSuchMethodException {
        Method method1 = c.getMethod("M1");
        Method method2 = c.getMethod("M2");
        long method1Address = MemoryWrapper.getMethodAddress(method1);
        long method2Address = MemoryWrapper.getMethodAddress(method2);
        return method2Address - method1Address;
    }

    public static void replaceMethod(Method oldMethod, Method newMethod, long size) {
        long dstAddress = MemoryWrapper.getMethodAddress(oldMethod);
        long srcAddress = MemoryWrapper.getMethodAddress(newMethod);
        MemoryWrapper.memcpy(dstAddress, srcAddress, size);
    }

    public static void replaceMethod(Method o, Method n) {
        long l = 0;
        try {
            l = measureMethodSize(MeasureArtMethodSize.class);
            replaceMethod(o, n, l);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void replaceMethod(Class a) {
        try {
            String name = a.getName();
            Class oldClass = null;
            oldClass = Class.forName(name);
            Method[] methods = a.getMethods();
            for (Method m : methods) {
                boolean s = m.isAnnotationPresent(Replace.class);
                if (s) {
                    Method oldMethod = oldClass.getMethod(m.getName());
                    replaceMethod(oldMethod, m);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
