package com.wjx.nio.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author WangJX
 * @Date 2021/7/3 14:41
 * @Description
 */
public class WriteClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8080));

        int count = 0;
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(16);
            count += sc.read(byteBuffer);
            System.out.println(count);
            byteBuffer.clear();
        }

    }
}
