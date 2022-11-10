package com.wjx.concurrent.createthred;

/**
 * @Author WangJX
 * @Date 2021/11/28 20:54
 * @Description
 */
public class TestRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("继承Runnable接口创建线程开始");
        TestRunnable testRunnable = new TestRunnable();
        new Thread(testRunnable).start();
        System.out.println("继承Runnable接口创建线程结束");
    }
}

class TestRunnable implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}