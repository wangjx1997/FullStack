package com.wjx.concurrent.createthred;

/**
 * @Author WangJX
 * @Date 2021/11/28 18:23
 * @Description
 */
public class TestThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("继承Thread类创建线程开始");
        TestThread testThread = new TestThread();
//        testThread.start();
        testThread.run();
        System.out.println("继承Thread类创建线程结束");
    }

}

class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println(getName());
    }
}