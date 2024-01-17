package com.wjx.event.v4;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

/**
 * @Author WangJX
 * @Date 2024/1/14 11:26
 * @Description  通用事件
 */
public class BaseEventV4_1<T> extends ApplicationEvent implements ResolvableTypeProvider {
    private String type;
    public BaseEventV4_1(T source) {
        super(source);
    }

    public BaseEventV4_1(Object source, String type) {
        super(source);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getSource()));
    }
}
