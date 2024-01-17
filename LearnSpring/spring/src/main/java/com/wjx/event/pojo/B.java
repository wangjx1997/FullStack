package com.wjx.event.pojo;

/**
 * @Author WangJX
 * @Date 2024/1/14 17:03
 * @Description
 */
public class B {

    private String b;

    public B() {
    }

    public B(String b) {
        this.b = b;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" +
                "b='" + b + '\'' +
                '}';
    }
}
