package com.wjx.proxy.day01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author WangJX
 * @Date 2022/4/5 17:00
 * @Description
 */
public class InvocationHandlerImpl implements InvocationHandler {
    private Object object;
    public InvocationHandlerImpl(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前");
        System.out.println("Method:" + method);
        Object invoke = method.invoke(object, args);
        System.out.println("干点事");
        return invoke;
    }
}
