package com.wjx.concurrent;

/**
 * @Author WangJX
 * @Date 2021/8/5 10:21
 * @Description
 */
public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
        // 在这里新建了一个线程
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                // 当这里调用了set方法，进一步调用了ThreadLocal的set方法是，
                // 会将ThreadLocal变量存储到该线程的ThreadLocalMap类型的成员变量threadLocals中，
                // 注意的是这个threadLocals变量是Thread线程的一个变量，而不是ThreadLocal类的变量。
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }

    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

}
