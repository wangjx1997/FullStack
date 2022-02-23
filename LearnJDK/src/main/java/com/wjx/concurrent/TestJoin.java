package com.wjx.concurrent;

/**
 * 如何让两个线程依次执行？
 * A,B两个线程，分别依次打印1-5
 *
 * @Author WangJX
 * @Date 2021/12/1 22:13
 * @Description
 */
public class TestJoin {

    public static void main(String[] args) {
        // 使用
//        demo1();
//         使用thread.join
        demo2();
    }

    private static void demo1() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("B");
            }
        });
        B.start();
        A.start();
    }

    private static void demo2() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 A");
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printNumber("B");
            }
        });
        B.start();
        A.start();
    }
    private static void printNumber(String threadName) {
        int i=0;
        while (i++ < 5) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print: " + i);
        }
    }
}
