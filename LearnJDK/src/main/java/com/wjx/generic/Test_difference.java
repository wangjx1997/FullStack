package com.wjx.generic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test_difference {
    public static void main(String[] args) {
        /*---------------------测试多重限定符---------------------*/
        ArrayList list = new ArrayList<>();
        ArrayDeque deque = new ArrayDeque<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        //多重限定时，在编译的时候取最小范围或共同子类
        test2(list);
//        test3(list); //编译报错
        //编译报错
//        test2(deque);
//        test3(deque);
        //编译通过
        test2(linkedList);
        test3(linkedList);
    }
    //可以进行多重限定
    public static <T extends List & Collection> void test2(T t) {
    }
    //可以进行多重限定
    public static <T extends Queue & List> void test3(T t) {
    }
    //编译报错，无法进行多重限定
//    public static <? extends List & Collection> void test4(List<T> dest, List<T> src){
//
//    }
}