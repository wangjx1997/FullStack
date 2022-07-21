package org.wjx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author WangJX
 * @Date 2022/3/16 10:07
 * @Description
 */
public class MyTest {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
