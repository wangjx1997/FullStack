package com.wjx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author WangJX
 * @Date 2022/4/28 11:09
 * @Description
 */
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanLifeCycleConfig.class);
        context.refresh();
        System.out.println("启动完成");
        context.close();
    }
}
