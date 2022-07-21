package com.wjx.nio.c2;

import java.nio.ByteBuffer;

import static com.wjx.nio.util.ByteBufferUtil.debugAll;

/**
 * @Author WangJX
 * @Date 2021/7/18 17:08
 * @Description
 */
public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        debugAll(byteBuffer);
//        byteBuffer.put(new byte[]{'a', 'b', 'c', 'd'});
        byteBuffer.put(new byte[]{'a'});
        debugAll(byteBuffer);
        byteBuffer.flip();

//        byteBuffer.get(new byte[4]);
//        debugAll(byteBuffer);
//        byteBuffer.rewind();
//        System.out.println((char) byteBuffer.get());

        // mark & reset
        // mark 做一个标记，记录position位置， reset 是将position重置到mark的位置
        /*System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        byteBuffer.mark(); // 加标记  索引2的位置
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        byteBuffer.reset(); // 将position重置到索引2
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());*/

        // get(i) 不会改变索引的位置
//        System.out.println((char)byteBuffer.get(3));
        debugAll(byteBuffer);
    }
}
