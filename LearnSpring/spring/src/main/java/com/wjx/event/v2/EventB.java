package com.wjx.event.v2;

import com.wjx.event.pojo.B;

/**
 * @Author WangJX
 * @Date 2024/1/14 10:57
 * @Description
 */
public class EventB {
    private B b;
    private String type;

    public EventB(B b, String type) {
        this.b = b;
        this.type = type;
    }

    @Override
    public String toString() {
        return "EventB{" +
                "b=" + b +
                ", type='" + type + '\'' +
                '}';
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
