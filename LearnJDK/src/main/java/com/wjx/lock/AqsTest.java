package com.wjx.lock;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author WangJX
 * @Date 2023/6/8 17:29
 * @Description
 */
public class AqsTest {
    static ReentrantLock reentrantLock = new ReentrantLock();
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws IOException {
        AqsTest aqsTest = new AqsTest();

        new Thread(aqsTest::wait1).start();
//
//        new Thread(aqsTest::wait1).start();
//
//        new Thread(aqsTest::wait1).start();


        System.in.read();
//        atomicBoolean.compareAndSet(false,true);
    }

    private void wait1(){
        reentrantLock.lock();
        try {
            while (!atomicBoolean.get()) {
                atomicBoolean.get();
            }
        }finally {
            reentrantLock.unlock();
        }
    }
}

