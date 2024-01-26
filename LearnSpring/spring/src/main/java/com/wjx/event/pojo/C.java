package com.wjx.event.pojo;

import org.springframework.context.ApplicationEvent;

/**
 * @Author WangJX
 * @Date 2024/1/21 17:39
 * @Description
 */
public class C extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public C(Object source) {
        super(source);
    }
}
