package com.wjx.concurrent.stopthred;

import java.util.concurrent.TimeUnit;

/**
 * @Author WangJX
 * @Date 2022/8/21 11:23
 * @Description  interrupt()  线程处于阻塞状态
 */
public class Demo1 {

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                //循环处理业务
                //下面模拟阻塞代码
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.interrupt();
    }
}
