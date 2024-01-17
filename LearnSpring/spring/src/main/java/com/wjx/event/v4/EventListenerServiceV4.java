package com.wjx.event.v4;

import com.wjx.event.pojo.A;
import com.wjx.event.pojo.B;
import org.springframework.context.event.EventListener;

/**
 * @Author WangJX
 * @Date 2024/1/14 17:09
 * @Description
 */

public class EventListenerServiceV4 {


    @EventListener
    public void eventA(BaseEventV4<A> a){
        System.out.println(a);
    }


    @EventListener
    public void eventB(BaseEventV4<B> b){
        System.out.println(b);
    }
}
