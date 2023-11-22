package com.wjx.str;

import java.lang.reflect.Field;

/**
 * @Author WangJX
 * @Date 2023/8/7 16:58
 * @Description
 */
public class StringTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String str = new String("abc");
        System.out.println(String.class.getName() + "@" + Integer.toHexString(System.identityHashCode(str)));

        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);

        value.set(str, "abcd".toCharArray());
        System.out.println(String.class.getName() + "@" + Integer.toHexString(System.identityHashCode(str)));
        System.out.println(str);
    }
}
