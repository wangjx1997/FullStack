package com.wjx.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 线程 D 在A、B、C都同步执行完毕后执行
 *
 * 创建一个计数器，并设置一个初始值， CountdownLatch countDownLatch = new CountDownLatch(3);
 *
 * 调用countDownLatch.await()进入等待状态，直到计数值变为0；
 *
 * 在其他线程调用countDownLatch.countDown()，该方法会将计数值减一；
 *
 * 当计数器的值变为 0 时，countDownLatch.await()等待线程中的方法会继续执行下面的代码。
 *
 * @Author WangJX
 * @Date 2021/11/28 10:24
 * @Description
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        runDAfterABC();
    }


    public static void runDAfterABC() {
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        new Thread(() -> {
            System.out.println("INFO: D 等待 A B C 运行完成");
            try {
                countDownLatch.await();
                System.out.println("INFO: A B C 运行完成，D 开始运行");
                System.out.println("D is working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (char threadName = 'A'; threadName <= 'C' ; threadName++) {
            final String name = String.valueOf(threadName);
            new Thread(() -> {
                System.out.println(name + " is working");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " finished");
                countDownLatch.countDown();
            }).start();
        }
    }
}
