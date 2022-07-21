package com.wjx.proxy.day01;

import java.lang.reflect.Proxy;

/**
 * @Author WangJX
 * @Date 2022/4/5 17:12
 * @Description
 */
public class ProxyMain {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        UserService o = (UserService)Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), new InvocationHandlerImpl(service));
        System.out.println("代理对象类型："+o.getClass().getName());
        System.out.println(o.sayHello());
//        System.out.println(o.sayGoodBye());

//        Map<Integer, Object> map = new HashMap<>();
//        map.put(0, "2");
//        map.put(1, "1");
//        map.put(2, "1");
//        for (Map.Entry<Integer, Object> stringObjectEntry : map.entrySet()) {
//            Integer key = stringObjectEntry.getKey();
//        }
    }
}
