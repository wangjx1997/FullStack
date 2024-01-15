package com.wjx.event.v1;

import com.wjx.event.pojo.A;
import com.wjx.event.pojo.B;
import org.springframework.context.event.EventListener;

/**
 * @Author WangJX
 * @Date 2024/1/15 17:09
 * @Description
 */

public class EventListenerServiceV1 {


    @EventListener
    public void eventA(A a){
        System.out.println(a);
    }


    @EventListener
    public void eventB(B b){
        System.out.println(b);
    }
}
