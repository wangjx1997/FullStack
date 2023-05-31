package com.wjx.concurrent.completablefuturetest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author WangJX
 * @Date 2022/11/30 16:28
 * @Description
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        createTask3();
    }

    /**
     * CompletableFuture<U>#supplyAsync(Supplier<U> supplier)
     * 重载版本，可以指定执行异步任务的Executor实现，如果不指定，默认使用ForkJoinPool.commonPool()，如果机器是单核的，则默认使用ThreadPerTaskExecutor，该类是一个内部类，每次执行execute都会创建一个新线程。
     * CompletableFuture<U>#supplyAsync(Supplier<U> supplier, Executor executor)
     * 创建异步执行任务，有返回值
     */
    public static void createTask3() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(true){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }

    /**
     * 创建异步执行任务，无返回值
     * CompletableFuture<Void>#runAsync(Runnable runnable)
     * 重载版本，可以指定执行异步任务的Executor实现，如果不指定，默认使用ForkJoinPool.commonPool()，如果机器是单核的，则默认使用ThreadPerTaskExecutor，该类是一个内部类，每次执行execute都会创建一个新线程。
     * CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     */
    public static void createTask2() throws ExecutionException, InterruptedException {

        // 创建异步执行任务，无返回值
        CompletableFuture<Void> cf = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
            }
        });
        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }



    /**
     * 异步创建任务
     * ExecutorService#submit(Callable<T> task)
     */
    public static void createTask1() throws ExecutionException, InterruptedException {
        // 创建异步执行任务:
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<Double> cf = executorService.submit(()->{
            System.out.println(Thread.currentThread()+" start,time->"+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(true){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread()+" exit,time->"+System.currentTimeMillis());
                return 1.2;
            }
        });
        System.out.println("main thread start,time->"+System.currentTimeMillis());
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("run result->"+cf.get());
        System.out.println("main thread exit,time->"+System.currentTimeMillis());
    }
}
