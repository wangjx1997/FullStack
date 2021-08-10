package com.wjx.netty.c2;

import com.wjx.netty.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @Author WangJX
 * @Date 2021/8/6 9:45
 * @Description
 */
public class TestByteBufferExam {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put("Hello,world\nI'm zhangsan\nho".getBytes());
        split1(byteBuffer);
        byteBuffer.put("w are you?\n".getBytes());
        split1(byteBuffer);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {

            //找到一条完整消息
            if (source.get(i) == '\n') {
                System.out.println("position-->:"+source.position());
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    // 向target写
                    target.put(source.get());
                }

                ByteBufferUtil.debugAll(target);
            }
        }


        source.compact();
    }


    private static void split1(ByteBuffer source) {
        source.flip();
        int ordLimit = source.limit();
        for (int i = 0; i < ordLimit; i++) {

            //找到一条完整消息
            if (source.get(i) == '\n') {
                System.out.println("position-->:"+source.position());
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                source.limit(i + 1);
                target.put(source);
                ByteBufferUtil.debugAll(target);
                source.limit(ordLimit);
            }
        }
        source.compact();
    }
}
