package com.wjx.nio.c4;

import java.nio.channels.SelectionKey;

/**
 * @Author WangJX
 * @Date 2021/7/3 14:06
 * @Description
 */
public class WriteServer {
/*
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while(true) {
            selector.select();

            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey sckey = sc.register(selector, SelectionKey.OP_READ);
                    // 1. 向客户端发送内容
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 3000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    int write = sc.write(buffer);
                    // 3. write 表示实际写了多少字节
                    System.out.println("实际写入字节:" + write);
                    // 4. 如果有剩余未读字节，才需要关注写事件
                    if (buffer.hasRemaining()) {
                        // read 1  write 4
                        // 在原有关注事件的基础上，多关注 写事件
                        sckey.interestOps(sckey.interestOps() + SelectionKey.OP_WRITE);
                        // 把 buffer 作为附件加入 sckey
                        sckey.attach(buffer);
                    }
                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(buffer);
                    System.out.println("实际写入字节:" + write);
                    if (!buffer.hasRemaining()) { // 写完了
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                        key.attach(null);
                    }
                }
            }
        }
    }
*/
//    public static void main(String[] args) throws IOException {
//        ServerSocketChannel ssc = ServerSocketChannel.open();
//        ssc.configureBlocking(false);
//        Selector selector = Selector.open();
//        ssc.register(selector, SelectionKey.OP_ACCEPT);
//        ssc.bind(new InetSocketAddress(8080));
//        while (true) {
//            selector.select();
//            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
//            while (iter.hasNext()) {
//                SelectionKey key = iter.next();
//                //
//                iter.remove();
//                if (key.isAcceptable()) {
//                    SocketChannel sc = ssc.accept();
//                    sc.configureBlocking(false);
//                    SelectionKey sckey = sc.register(selector, SelectionKey.OP_READ);
//                    // 1. 向客户端发送内容
//                    StringBuilder sb = new StringBuilder();
//                    for (int i = 0; i < 3000000; i++) {
//                        sb.append("a");
//                    }
//                    ByteBuffer byteBuffer = Charset.defaultCharset().encode(sb.toString());
//                    int write = sc.write(byteBuffer);
//                    // 3. write 表示实际写了多少字节
//                    System.out.println("实际写入字节:" + write);
//                    // 4. 如果有剩余未读字节，才需要关注写事件
//                    if (byteBuffer.hasRemaining()) {
////                        sckey.interestOps(sckey.interestOps() + SelectionKey.OP_WRITE);
////                        sckey.attach(byteBuffer);
//                        // read 1  write 4
//                        // 在原有关注事件的基础上，多关注 写事件
//                        sckey.interestOps(sckey.interestOps() + SelectionKey.OP_WRITE);
//                        // 把 buffer 作为附件加入 sckey
//                        sckey.attach(byteBuffer);
//                    }
//
//                } else if (key.isWritable()) {
//                    ByteBuffer buffer = (ByteBuffer)key.attachment();
//                    SocketChannel sc = (SocketChannel)key.channel();
//                    int write = sc.write(buffer);
//                    System.out.println("实际写入字节:" + write);
//                    if (!buffer.hasRemaining()) {// 写完了
//                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
//                        key.attach(null);
//                    }
//                } else if (key.isReadable()) {
//                    SocketChannel sc = (SocketChannel) key.channel();
//                    ByteBuffer buffer = ByteBuffer.allocate(16);
//                    int read = sc.read(buffer);
//                    if (read == -1) {
//                        key.cancel();
//                        sc.close();
//                    }
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        System.out.println(SelectionKey.OP_READ + SelectionKey.OP_WRITE);
        System.out.println(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        System.out.println(SelectionKey.OP_READ & SelectionKey.OP_WRITE);
    }
}
