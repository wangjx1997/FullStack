package com.wjx.recyclertest;

import io.netty.util.Recycler;

/**
 * @Author WangJX
 * @Date 2022/12/12 17:03
 * @Description
 */
public class UserCache {

    private static final Recycler<User> userRecycler = new Recycler<User>() {

        @Override

        protected User newObject(Handle<User> handle) {

            return new User(handle);

        }

    };

    static final class User {

        private String name;

        private Recycler.Handle<User> handle;

        public void setName(String name) {

            this.name = name;

        }

        public String getName() {

            return name;

        }

        public User(Recycler.Handle<User> handle) {

            this.handle = handle;

        }

        public void recycle() {

            handle.recycle(this);

        }

    }

    public static void main(String[] args) {

        User user1 = userRecycler.get(); // 1、从对象池获取 User 对象

        user1.setName("hello"); // 2、设置 User 对象的属性

        user1.recycle(); // 3、回收对象到对象池

        User user2 = userRecycler.get(); // 4、从对象池获取对象

        System.out.println(user2.getName());

        System.out.println(user1 == user2);

    }

}

