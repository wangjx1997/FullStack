package com.wjx.concurrent.createthred;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author WangJX
 * @Date 2021/11/28 21:10
 * @Description
 */
public class TestFutureTaskMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
        System.out.println("通过Callable和FutureTask创建线程开始");
        TestFutureTask testFutureTask = new TestFutureTask();
        FutureTask<String> futureTask = new FutureTask<String>(testFutureTask);
        new Thread(futureTask).start();  // 启动线程
        System.out.println("通过Callable和FutureTask创建线程结束");
        System.out.println(futureTask.get());  // 异步获取结果
        System.out.println("获取结果结束");
    }
}

class TestFutureTask implements Callable<String> {

    public String call() throws Exception {

        System.out.println(Thread.currentThread().getName());
        return "1128TEST";
    }
}