package com.wjx.reflectest;

/**
 * @Author WangJX
 * @Date 2022/8/21 0:14
 * @Description
 */
public class A {

    private String kk = "1";

    public A() {
    }

    @Override
    public String toString() {
        return "A{" +
                "kk='" + kk + '\'' +
                '}';
    }

    public A(String kk) {
        this.kk = kk;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }
}
