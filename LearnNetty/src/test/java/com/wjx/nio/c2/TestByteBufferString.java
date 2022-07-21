package com.wjx.nio.c2;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.wjx.nio.util.ByteBufferUtil.debugAll;

/**
 * 字符串与字节转换
 * @Author WangJX
 * @Date 2021/7/20 15:49
 * @Description
 */
public class TestByteBufferString {
    public static void main(String[] args) {
        // 1.字符串转换ByteBuffer   还是写模式
        ByteBuffer b = ByteBuffer.allocate(10);
        b.put("hello1".getBytes());
        debugAll(b);

        // 2. Charset   转换完切换成了读模式
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("hello2");
        debugAll(b2);

        // 3.wrap
        ByteBuffer b3 = ByteBuffer.wrap("hello3".getBytes());
        debugAll(b3);

        // 转换字符串
        b.flip(); // 需要切换读模式
        String s = StandardCharsets.UTF_8.decode(b).toString();
        System.out.println(s);

        String s1 = StandardCharsets.UTF_8.decode(b2).toString();
        System.out.println(s1);
    }
}
