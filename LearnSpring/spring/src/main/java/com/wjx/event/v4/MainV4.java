package com.wjx.event.v4;

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
public class MainV4 {


    @Configuration
    static class Config {

        @Bean
        public EventListenerServiceV4 eventListenerServiceV4() {
            return new EventListenerServiceV4();
        }


        @Bean
        public EventListenerServiceV4_1 eventListenerServiceV4_1() {
            return new EventListenerServiceV4_1();
        }
    }

    // 测试 事件发布
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainV4.Config.class);

        context.publishEvent(new BaseEventV4<A>(new A("a"), OpEnum.ADD.getType()));
        context.publishEvent(new BaseEventV4<B>(new B("b"), OpEnum.DEL.getType()));


        context.publishEvent(new BaseEventV4_1<A>(new A("a"), OpEnum.ADD.getType()));
        context.publishEvent(new BaseEventV4_1<B>(new B("b"), OpEnum.ADD.getType()));
    }
}
