package com.wjx;

/**
 * @Author WangJX
 * @Date 2023/10/12 10:12
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        new Main().a();
    }
    public void a(){
        User user = new User();
        System.out.println(user);
        b(user);
        System.out.println(user);
    }
    public void b(User user) {
        user = new User();
    }
}
class User{}