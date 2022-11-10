package com.wjx.reflectest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author WangJX
 * @Date 2022/8/21 0:14
 * @Description
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> a = Class.forName("com.wjx.reflectest.A");
        for (Field declaredField : a.getDeclaredFields()) {
            System.out.println(declaredField.toString());
        }


        for (Method method : a.getDeclaredMethods()) {
            System.out.println(method.toString());
        }

        for (Constructor<?> declaredConstructor : a.getDeclaredConstructors()) {
            System.out.println(declaredConstructor.toString());
        }

        Object o = a.newInstance();
        System.out.println(o);

        Constructor<?> declaredConstructor = a.getDeclaredConstructor(String.class);
        Object o1 = declaredConstructor.newInstance("2");
        System.out.println(o1);
    }
}
