package com.wjx.jvmloading;

/**
 * @Author WangJX
 * @Date 2022/8/14 22:36
 * @Description
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init");
    }

    public final static int INT = 111;
}
