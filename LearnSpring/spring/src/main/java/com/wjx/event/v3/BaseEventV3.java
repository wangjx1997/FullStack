package com.wjx.event.v3;

/**
 * @Author WangJX
 * @Date 2024/1/14 11:26
 * @Description  通用事件
 */
public class BaseEventV3<T> /*implements ResolvableTypeProvider*/ {
    private T  t;
    private String type;

    public BaseEventV3(T t, String type) {
        this.t = t;
        this.type = type;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


//    @Override
//    public ResolvableType getResolvableType() {
//        return ResolvableType.forClassWithGenerics(this.getClass(), ResolvableType.forInstance(this.t));
//    }
}
