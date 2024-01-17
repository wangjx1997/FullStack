package com.wjx.event.v4;

import org.springframework.context.ApplicationEvent;

/**
 * @Author WangJX
 * @Date 2024/1/14 11:26
 * @Description  通用事件
 */
public class BaseEventV4<T> extends ApplicationEvent {
    private String type;
    public BaseEventV4(T source) {
        super(source);
    }

    public BaseEventV4(Object source, String type) {
        super(source);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
