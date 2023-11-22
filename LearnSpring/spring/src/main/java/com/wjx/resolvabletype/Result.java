package com.wjx.resolvabletype;

/**
 * @Author WangJX
 * @Date 2023/10/10 10:23
 * @Description
 */
public class Result<T> {

    private T data;


    public static <T> T a(T a) {
        return a;
    }
}
