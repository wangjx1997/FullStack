package com.wjx;

/**
 * @Author WangJX
 * @Date 2022/4/19 17:29
 * @Description
 */
public class Arr {
    public static void main(String[] args) {
        String[] a = new String[2];
        Object[] b = a;
        a[0] = "hi";
        b[1] = Integer.valueOf(42);
    }
}
