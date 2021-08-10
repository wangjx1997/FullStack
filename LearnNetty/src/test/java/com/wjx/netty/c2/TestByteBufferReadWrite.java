package com.wjx.netty.c2;

import com.wjx.netty.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @Author WangJX
 * @Date 2021/7/4 23:32
 * @Description
 */
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte) 0x61);
        ByteBufferUtil.debugAll(byteBuffer);

        byteBuffer.put(new byte[]{0x62, 0x63, 0x64});

        ByteBufferUtil.debugAll(byteBuffer);

        byteBuffer.flip();
        System.out.println(byteBuffer.get());

        ByteBufferUtil.debugAll(byteBuffer);

        byteBuffer.compact();
        ByteBufferUtil.debugAll(byteBuffer);

        byteBuffer.put(new byte[]{0x65, 0x66});
        ByteBufferUtil.debugAll(byteBuffer);

    }
}
