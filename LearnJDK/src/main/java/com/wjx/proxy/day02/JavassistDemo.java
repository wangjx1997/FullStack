package com.wjx.proxy.day02;

/**
 * @Author WangJX
 * @Date 2022/5/24 11:09
 * @Description
 */
public class JavassistDemo {
    private String prop = "MyName";

    public void setProp(String var1) {
        this.prop = var1;
    }

    public String getProp() {
        return this.prop;
    }

    public JavassistDemo() {
        this.prop = "MyName";
    }

    public void execute() {
        System.out.println("execute():" + this.prop);
    }
}
