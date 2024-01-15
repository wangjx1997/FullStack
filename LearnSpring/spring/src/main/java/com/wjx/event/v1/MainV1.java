package com.wjx.event.v1;

import com.wjx.event.pojo.A;
import com.wjx.event.pojo.B;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangJX
 * @Date 2024/1/15 17:05
 * @Description
 */
public class MainV1 {

    @Configuration
    static class Config{

        @Bean
        public EventListenerServiceV1 eventListenerServiceV1(){
            return new EventListenerServiceV1();
        }
    }

    // 测试 事件发布
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainV1.Config.class);

        context.publishEvent(new A());
        context.publishEvent(new B());
    }
}
