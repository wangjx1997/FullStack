package com.wjx.generic;

/**
 * 泛型方法与可变参数
 */
public class Test_GenericMethod02 {
    public static void main(String[] args) {
        print("123",753,123.12);
    }
    //必须是三个点
    public static <T> void print(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }
}