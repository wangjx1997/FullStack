package com.wjx.nio.c4;

import com.wjx.nio.c2.TestByteBufferExam;
import com.wjx.nio.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author WangJX
 * @Date 2021/8/10 17:10
 * @Description
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
//        blockMode();
//        nonBlockMode();
//        selectorMode();
        selectorMsgMode();
    }
    /**
     * 非阻塞 多路复用 模式服务端  处理消息边界
     * @throws IOException
     */
    private static void selectorMsgMode() throws IOException {
        // 1、 创建 selector 管理多个channel
        Selector selector = Selector.open();

//        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置非阻塞
        ssc.configureBlocking(false);

        // 2. 建立selector 和channel的联系（注册）
        // SelectionKey 就是将来事件发生后，通过它可以知道事件和那个channel的事件
        // 四种事件
        // accept - 服务器端在有连接请求时触发
        // connect - 是客户端，连接建立后触发
        // read - 可读事件
        // write - 可写事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // sscKey 只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key：{}", sscKey);
        ssc.bind(new InetSocketAddress(8080));
        while (true) {

            // 3.select 方法 ，没有事件发生，线程阻塞。有事件，线程才会恢复运行
            selector.select();
            // 4. 处理事件, selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                log.debug("key：{}", key);
                // 5.区分事件类型
                // 如果是accept事件
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    // 将一个byteBuffer 作为附件关联到selectionKey上
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                } else if (key.isReadable()) {
                    // 如果是read事件
                    try {
                        // 拿到触发事件的channel
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 获取selectionKey上的附件
                        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                        // 如果是正常断开 read方法的返回值是-1
                        int read = channel.read(byteBuffer);
                        if (read == -1) {
                            key.cancel();
                        }else {
                            TestByteBufferExam.split(byteBuffer);
                            if (byteBuffer.position() == byteBuffer.limit()) {
                                ByteBuffer newByteBuffer = ByteBuffer.allocate(byteBuffer.capacity() * 2);
                                byteBuffer.flip();
                                newByteBuffer.put(byteBuffer);
                                key.attach(newByteBuffer);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 因为客户端断开了，因此需要将key取消（从selector的keys集合中真正的删除）
                        key.cancel();
                    }

                }
                // 处理完要从selectedKeys集合中删除，否则下次处理就会有问题
                iterator.remove();

            }

        }
    }
    /**
     * 非阻塞 多路复用 模式服务端
     * @throws IOException
     */
    private static void selectorMode() throws IOException {
        // 1、 创建 selector 管理多个channel
        Selector selector = Selector.open();

//        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置非阻塞
        ssc.configureBlocking(false);

        // 2. 建立selector 和channel的联系（注册）
        // SelectionKey 就是将来事件发生后，通过它可以知道事件和那个channel的事件
        // 四种事件
        // accept - 服务器端在有连接请求时触发
        // connect - 是客户端，连接建立后触发
        // read - 可读事件
        // write - 可写事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // sscKey 只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key：{}", sscKey);
        ssc.bind(new InetSocketAddress(8080));
        while (true) {

            // 3.select 方法 ，没有事件发生，线程阻塞。有事件，线程才会恢复运行
            selector.select();
            //  4. 处理事件, selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                log.debug("key：{}", key);
                // 5.区分事件类型
                // 如果是accept事件
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                } else if (key.isReadable()) {
                    // 如果是read事件
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 如果是正常断开 read方法的返回值是-1
                        int read = channel.read(byteBuffer);
                        if (read == -1) {
                            key.cancel();
                        }else {
                            byteBuffer.flip();
                            ByteBufferUtil.debugAll(byteBuffer);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 因为客户端断开了，因此需要将key取消（从selector的keys集合中真正的删除）
                        key.cancel();
                    }

                }
                // 处理完要从selectedKeys集合中删除，否则下次处理就会有问题
                iterator.remove();

            }

        }
    }

    /**
     * 非阻塞 模式服务端
     *
     * @throws IOException
     */
    private static void nonBlockMode() throws IOException {
        // 使用 nio 来理解阻塞模式
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1.创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置非阻塞
        ssc.configureBlocking(false);
        // 2.绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {

            // 4. accept 建立与客户端连接  SocketChannel 用来与客户端之间的通讯
            // 非阻塞 线程会继续运行，如果没有连接建立 sc是null
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                log.debug("connecting... {}", sc);
                // 客户端连接设置非阻塞
                sc.configureBlocking(false);
                channels.add(sc);
            }

            for (SocketChannel channel : channels) {
                // 接收客户端信息
                // 非阻塞下  线程会继续运行 没有读到数据 read为0
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    ByteBufferUtil.debugAll(buffer);
                    buffer.clear();
                    log.debug("after read... {}", channel);
                }

            }

        }
    }

    /**
     * 阻塞模式 服务端
     * @throws IOException
     */
    private static void blockMode() throws IOException {
        // 使用 nio 来理解阻塞模式 单线程
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1.创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2.绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
        // 3.连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {

            // 4. accept 建立与客户端连接  SocketChannel 用来与客户端之间的通讯
            log.debug("connecting...");
            // 阻塞方法，线程停止运行
            SocketChannel sc = ssc.accept();
            log.debug("connecting... {}", sc);
            channels.add(sc);

            for (SocketChannel channel : channels) {
                // 接收客户端信息
                log.debug("before read... {}", channel);
                // 阻塞方法，线程停止运行
                channel.read(buffer);
                buffer.flip();
                ByteBufferUtil.debugAll(buffer);
                buffer.clear();
                log.debug("after read... {}", channel);

            }

        }
    }
}
