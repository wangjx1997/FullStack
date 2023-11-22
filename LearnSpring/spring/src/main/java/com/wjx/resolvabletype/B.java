package com.wjx.resolvabletype;

import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

/**
 * @Author WangJX
 * @Date 2023/10/10 11:21
 * @Description
 */
public class B extends A<String> {

    public static void main(String[] args) {
        B b = new B();
        ResolvableType resolvableType = ResolvableType.forField(ReflectionUtils.findField(b.getClass(), "t"));
        ResolvableType resolvableTypes = ResolvableType.forClass(b.getClass());

        System.out.println(resolvableType.resolve());
    }
}
