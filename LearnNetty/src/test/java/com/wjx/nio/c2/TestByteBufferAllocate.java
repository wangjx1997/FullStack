package com.wjx.nio.c2;

import java.nio.ByteBuffer;

/**
 * @Author WangJX
 * @Date 2021/7/18 16:54
 * @Description
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(10));
        System.out.println(ByteBuffer.allocateDirect(10));
        /*
        java.nio.HeapByteBuffer[pos=0 lim=10 cap=10]    -java 堆内存 读写速率较低 受到GC的影响
        java.nio.DirectByteBuffer[pos=0 lim=10 cap=10]  -直接内存  读写效率高 （少一次拷贝） 不会受到GC影响 分配内存的效率低
        */
    }
}
