package com.wjx.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态方法有一种情况需要注意一下，那就是在类中的静态方法使用泛型：静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
 *
 * 即：如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。
 * @param <T>
 */
public class StaticGenerator<T> {
    /**
     * 1、如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
          "StaticGenerator cannot be refrenced from static context"
     * 2、泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。换句话说，
     * 泛型方法所属的类是不是泛型类都没有关系。
     * 3、泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在初始化类时确定,所以无所谓
     */
    public static <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list = new ArrayList<>();
        for(E e : arr){
            list.add(e);
        }
        return list;
    }
}