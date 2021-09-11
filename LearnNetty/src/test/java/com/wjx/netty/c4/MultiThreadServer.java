package com.wjx.netty.c4;

import com.wjx.netty.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author WangJX
 * @Date 2021/8/16 18:49
 * @Description
 */
@Slf4j
public class MultiThreadServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        WorkerEventLoop workerEventLoop = new WorkerEventLoop("work-0");
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel  channel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    log.debug("{} connected", sc.getRemoteAddress());
                    workerEventLoop.register();
                    sc.register(workerEventLoop.selector, SelectionKey.OP_READ);
                }
            }
        }
    }

    static class WorkerEventLoop implements Runnable {
        private Selector selector;
        private Thread thread;
        private String name;
        private boolean start = false;

        public WorkerEventLoop(String name) {
            this.name = name;
        }

        public void register() throws IOException {
            log.debug("{}启动", name);
            if (!start) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                start = true;
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    if (iterator.hasNext()) {
                        SelectionKey k = iterator.next();
                        iterator.remove();
                        SocketChannel socketChannel = (SocketChannel) k.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
                        socketChannel.read(byteBuffer);
                        ByteBufferUtil.debugAll(byteBuffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
