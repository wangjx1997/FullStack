package com.wjx.event.v2;

import org.springframework.context.event.EventListener;

/**
 * @Author WangJX
 * @Date 2024/1/14 17:09
 * @Description
 */

public class EventListenerServiceV2 {


    @EventListener
    public void eventA(EventA a){
        System.out.println(a);
    }


    @EventListener
    public void eventB(EventB b){
        System.out.println(b);
    }
}
