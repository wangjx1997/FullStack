package com.wjx.concurrent.createthred;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangJX
 * @Date 2021/11/29 20:53
 * @Description
 */
public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("通过线程池创建线程");
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });

//        executorService.shutdown();
    }
}
