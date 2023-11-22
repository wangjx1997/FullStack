package com.wjx.resolvabletype;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;

public class MyMap extends HashMap<Integer, List<String>> {




    public static void main(String[] args) {
        //创建MyMap对应的ResolvableType
        ResolvableType myMapType = ResolvableType.forClass(MyMap.class);




        //获取父类HashMap对应的ResolvableType
        ResolvableType hashMapType = myMapType.getSuperType();

        //获取第一个泛型参数对应的ResolvableType
        ResolvableType firstGenericType = hashMapType.getGeneric(0);


        //获取第一个泛型参数对应的ResolvableType对应的class类型，也就是Integer的class类型
        Class<?> firstGenericClass = firstGenericType.resolve();

        System.out.println(firstGenericClass);


        //HashMap第二个泛型参数的对应的ResolvableType，也就是List<String>
        ResolvableType secondGenericType = hashMapType.getGeneric(1);
        System.out.println(secondGenericType.resolve());
//HashMap第二个泛型参数List<String>的第一个泛型类型String对应的ResolvableType
        ResolvableType secondFirstGenericType = secondGenericType.getGeneric(0);
//这样就获取到了List<String>的泛型类型String
        Class<?> secondFirstGenericClass = secondFirstGenericType.resolve();

        System.out.println(secondFirstGenericClass);
    }

}