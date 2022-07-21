package com.wjx.enumt;

/**
 * @Author WangJX
 * @Date 2022/6/10 17:27
 * @Description
 */
public class Ex extends Exception{
    public Ex(Enum<? extends ExInterface> anEnum) {
        this.anEnum = anEnum;
    }

    private T1 t1;
    private Enum<? extends ExInterface> anEnum;
    public T1 getT1() {
        return t1;
    }

    public void setT1(T1 t1) {
        this.t1 = t1;
    }

    public Enum<? extends ExInterface> getAnEnum() {
        return anEnum;
    }

    public void setAnEnum(Enum<? extends ExInterface> anEnum) {
        this.anEnum = anEnum;
    }
}
