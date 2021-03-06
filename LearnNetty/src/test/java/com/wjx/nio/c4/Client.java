package com.wjx.nio.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Author WangJX
 * @Date 2021/8/10 17:40
 * @Description
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8080));
//        sc.write(Charset.defaultCharset().encode("0123\n456789abcdef"));
//        sc.write(Charset.defaultCharset().encode("0123456789abcdef3333\n"));
        sc.write(Charset.defaultCharset().encode("hi"));
        System.out.println("waiting.....");
        System.in.read();
    }
}
