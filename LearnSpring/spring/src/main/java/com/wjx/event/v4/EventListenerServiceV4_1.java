package com.wjx.event.v4;

import com.wjx.event.pojo.A;
import com.wjx.event.pojo.B;
import org.springframework.context.event.EventListener;

/**
 * @Author WangJX
 * @Date 2024/1/14 17:09
 * @Description
 */

public class EventListenerServiceV4_1 {


    @EventListener
    public void eventA(BaseEventV4_1<A> a){
        System.out.println(a);
    }


    @EventListener
    public void eventB(BaseEventV4_1<B> b){
        System.out.println(b);
    }
}
