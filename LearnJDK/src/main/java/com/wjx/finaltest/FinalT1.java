package com.wjx.finaltest;

/**
 * @Author WangJX
 * @Date 2022/7/21 10:35
 * @Description
 */
public class FinalT1 {

    // 私有的final属性 可以通过 构造赋值
    private final String b;
    private final String c = "1";

    public FinalT1(String b,String c) {
        this.b = b;
        // 不能第二次赋值
//        this.c = c;
    }

    public String getB() {
        return b;
    }

}
