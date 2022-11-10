package com.wjx.jvmloading;

/**
 * @Author WangJX
 * @Date 2022/8/14 22:14
 * @Description
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public static int value = 100;
}
