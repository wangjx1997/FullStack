package com.wjx.proxy.day03;

/**
 * @Author WangJX
 * @Date 2022/5/24 11:18
 * @Description
 */
public class CGLibTest { // 目标类
    public String method(String str) { // 目标方法
        System.out.println(str);
        return "CGLibTest.method():" + str;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        // 生成CBLibTest的代理对象
        CGLibTest proxyImp = (CGLibTest)
                proxy.getProxy(CGLibTest.class);
        // 调用代理对象的method()方法
        String result = proxyImp.method("test");
        System.out.println(result);
        // ----------------
        // 输出如下：
        // 前置代理
        // test
        // 后置代理
        // CGLibTest.method():test
    }
}
