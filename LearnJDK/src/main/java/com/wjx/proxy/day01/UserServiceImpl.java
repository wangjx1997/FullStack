package com.wjx.proxy.day01;

/**
 * @Author WangJX
 * @Date 2022/4/5 16:58
 * @Description
 */
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello() {
        return "hello";
    }

    @Override
    public String sayGoodBye() {
        return "goodBye";
    }
}
