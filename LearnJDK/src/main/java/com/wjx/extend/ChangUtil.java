package com.wjx.extend;

/**
 * @Author WangJX
 * @Date 2022/4/25 17:16
 * @Description
 */
public class ChangUtil {
    static Class aClass = ExtendB.class;

    private static ExtendParent chang1() {
        return new ExtendB();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
//        ExtendParent extendB = (ExtendParent) aClass.newInstance();
//
//        System.out.println(ExtendA.class.isInstance(extendB));
        ExtendParent extendParent = new ExtendParent();

        ExtendB extendB = (ExtendB) extendParent;

        System.out.println(extendB);
    }
}
