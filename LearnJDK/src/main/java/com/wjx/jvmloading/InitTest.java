package com.wjx.jvmloading;

/**
 * 以下场景不会初始化类
 *
 * @Author WangJX
 * @Date 2022/8/14 22:12
 * @Description
 */
public class InitTest {

    public static void main(String[] args) throws ClassNotFoundException {
        // 在使用类名获取class对象不会触发类的初始化
//        Class<SuperClass> x = SuperClass.class;
//        System.out.println(x);

        // Class.forName可以指定是否触发初始化
//        Class<?> aClass = Class.forName("com.wjx.jvmloading.SuperClass",false,InitTest.class.getClassLoader());
//        System.out.println(aClass);

        // 在使用classLoader 默认的 loadClass 加载 不会触发该类的初始化
//        Class<?> loadClass = ClassLoader.getSystemClassLoader().loadClass("com.wjx.jvmloading.SuperClass");
//        System.out.println(loadClass);

        // 定义对象数组，不会触发该类的初始化
//        SuperClass[] superClasses = new SuperClass[10];

        // 子类引用父类的静态字段，不会初始化子类，只会初始化父类
//        System.out.println(SubClass.value);
        //  SuperClass init
        //  100

        // 常量在编译时会将常量值存入使用该常量的类的常量池，该过程不会触发常量类的初始化
//        System.out.println(SubClass.INT);
    }


}
