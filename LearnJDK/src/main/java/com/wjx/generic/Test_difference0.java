package com.wjx.generic;

import java.util.ArrayList;
import java.util.List;
public class Test_difference0 {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();
        //编译报错
        //test(integerList, floatList);
        //编译通过
        test1(integerList, floatList);
        //编译通过
        test(integerList, integerList);
        test1(integerList, integerList);
    }
    // 通过 T 来 确保 泛型参数的一致性
    public static <T extends Number> void test(List<T> dest, List<T> src){
    }
    //通配符是 不确定的，所以这个方法不能保证两个 List 具有相同的元素类型
    public static void test1(List<? extends Number> dest, List<? extends Number> src){
    }
}