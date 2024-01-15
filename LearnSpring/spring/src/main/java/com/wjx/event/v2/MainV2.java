package com.wjx.event.v2;

import com.wjx.event.pojo.A;
import com.wjx.event.pojo.B;
import com.wjx.event.v1.EventListenerServiceV1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangJX
 * @Date 2024/1/15 17:16
 * @Description
 */
public class MainV2 {



    @Configuration
    static class Config{

        @Bean
        public com.wjx.event.v1.EventListenerServiceV1 eventListenerServiceV1(){
            return new EventListenerServiceV1();
        }
    }

    // 测试 事件发布
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainV2.Config.class);

        context.publishEvent(new A());
        context.publishEvent(new B());
    }
}
