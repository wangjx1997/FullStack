package com.wjx.event.v2;

import com.wjx.event.pojo.A;

/**
 * @Author WangJX
 * @Date 2024/1/14 17:42
 * @Description
 */
public class EventA {
    private A a;
    private String type;


    public EventA(A a, String type) {
        this.a = a;
        this.type = type;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EventA{" +
                "a=" + a +
                ", type='" + type + '\'' +
                '}';
    }
}
