package com.wjx.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * A、B、C这三个线程都需要分别准备，等三个线程都准备好后开始同时运行，我们应该如何做到这一点？
 *
 * 首先创建一个公共对象CyclicBarrier，并设置同时等待的线程数，CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
 *
 * 这些线程同时开始准备，准备好后，需要等待别人准备好，所以调用cyclicBarrier.await()方法等待别人；
 *
 * 当指定的需要同时等待的线程都调用了该cyclicBarrier.await()方法时，意味着这些线程准备好了，那么这些线程就会开始同时继续执行。
 *
 *
 * @Author WangJX
 * @Date 2021/11/28 10:45
 * @Description
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        runABCWhenAllReady();
    }

    public static void runABCWhenAllReady() {
        int count = 2;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count,()-> System.out.println("开始跑步"));
        Random random = new Random();
        for (char threadName = 'A'; threadName <= 'Z' ; threadName++) {
            final String name = String.valueOf(threadName);
            new Thread(() -> {
                int prepareTime = random.nextInt(10000);
                System.out.println(name + " 准备时间：" + prepareTime);
                try {
                    Thread.sleep(prepareTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " 准备好了，等待其他人");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
//                System.out.println(name + " 开始跑步");
            }).start();
        }
    }
}
