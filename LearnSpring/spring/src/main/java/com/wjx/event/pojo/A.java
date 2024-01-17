package com.wjx.event.pojo;

/**
 * @Author WangJX
 * @Date 2024/1/14 17:03
 * @Description
 */
public class A {

    private String a;

    public A() {
    }

    public A(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                '}';
    }
}
