package com.wjx.event.v3;

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
public class MainV3 {


    @Configuration
    static class Config {

        @Bean
        public EventListenerServiceV3 eventListenerServiceV3() {
            return new EventListenerServiceV3();
        }
    }

    // 测试 事件发布
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainV3.Config.class);

        context.publishEvent(new BaseEventV3<A>(new A("a"), OpEnum.ADD.getType()));
        context.publishEvent(new BaseEventV3<B>(new B("b"), OpEnum.DEL.getType()));
//        context.publishEvent(new PayloadApplicationEvent<>(context, new A("a")));
    }
}
