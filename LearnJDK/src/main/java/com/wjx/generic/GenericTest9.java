package com.wjx.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 有限制条件的通配符的使用
 */
public class GenericTest9 {
    /**
     * 结论：
     * ？ extends A ：
     *      G<? extends A> 可以作为G<A> 和 G<B>的父类，其中B是A的子类
     * ？ super A ：
     *      G<? super A> 可以作为G<A> 和 G<B>的父类，其中B是A的父类
     */
    @Test
    public void test01() {
        List<? extends Person1> list = null;
        List<? super Person1> list2 = null;

        List<Man> list3 = new ArrayList<>();
        list3.add(new Man("AA", 10));

        List<Person1> list4 = new ArrayList<>();
        list4.add(new Person1("AA"));

        List<Object> list5 = new ArrayList<>();
        list = list3;
//        list = list4;
        //list5 不能 自动向上转型为list
//        list = list5;
        Person1 person = list.get(0);
        //编译不通过
//        Man man = list.get(0);
        //list3 不能 自动向上转型为list2
//        list2 = list3;
        list2 = list4;
//        list2 = list5;
        Object object = list2.get(0);
        //编译不通过
//        Person p = list2.get(0);
        //写入数据：
//        编译都不通过
//        list.add(new Man("BB"));
//        list.add(new Person1());
//        list.add(new Object());
        list2.add(new Man("CC"));
        list2.add(new Person1());
    }
}
class Person1 {
    String name;
    public Person1() {
    }
    public Person1(String name) {
        this.name = name;
    }
}
class Man extends Person1{
    Integer age;
    public Man(String name) {
        super(name);
    }
    public Man(String name, Integer age) {
        super(name);
        this.age = age;
    }
}