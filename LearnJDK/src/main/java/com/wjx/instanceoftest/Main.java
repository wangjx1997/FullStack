package com.wjx.instanceoftest;

/**
 * @Author WangJX
 * @Date 2023/6/29 17:04
 * @Description
 */
public class Main {
    public static void main(String[] args) {


        int a = 1;
        Integer b = a;

        Integer integer = new Integer(1);
        System.out.println(b==integer);
        int d = integer.intValue();
        String c = null;
        System.out.println(b instanceof Integer);
        System.out.println(b instanceof Object);
        System.out.println(c instanceof  Object);  // false
    }
}
