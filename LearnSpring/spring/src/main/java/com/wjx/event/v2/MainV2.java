package com.wjx.event.v2;

import com.wjx.event.enums.OpEnum;
import com.wjx.event.pojo.A;
import com.wjx.event.pojo.B;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author WangJX
 * @Date 2024/1/14 17:16
 * @Description
 */
public class MainV2 {


    @Configuration
    static class Config {

        @Bean
        public EventListenerServiceV2 eventListenerServiceV2() {
            return new EventListenerServiceV2();
        }
    }

    // 测试 事件发布
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainV2.Config.class);

        context.publishEvent(new EventA(new A("a"), OpEnum.ADD.getType()));
        context.publishEvent(new EventB(new B("b"), OpEnum.DEL.getType()));
    }
}
