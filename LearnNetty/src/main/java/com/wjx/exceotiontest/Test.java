package com.wjx.exceotiontest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Author WangJX
 * @Date 2022/11/20 16:55
 * @Description
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Test.start(8080);
    }

    public static void start(int port) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup)

                    .channel(NioServerSocketChannel.class)

                    .localAddress(new InetSocketAddress(port))

                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override

                        public void initChannel(SocketChannel ch) {

                            ch.pipeline()
                                    .addLast(new SampleInBoundHandler("SampleInBoundHandlerA", true))
                                    .addLast(new SampleInBoundHandler("SampleInBoundHandlerB", true))
                                    .addLast(new SampleInBoundHandler("SampleInBoundHandlerC", false));
                            ch.pipeline()
                                    .addLast(new SampleOutBoundHandler("SampleOutBoundHandlerA"))
                                    .addLast(new SampleOutBoundHandler("SampleOutBoundHandlerB"))
                                    .addLast(new ExSampleOutBoundHandler("ExSampleOutBoundHandler"))
                                    .addLast(new SampleOutBoundHandler("SampleOutBoundHandlerC"));
                            ch.pipeline().addLast(new ExceptionHandler());

                        }

                    })

                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind().sync();

            System.out.println("Http Server started， Listening on " + port);

            f.channel().closeFuture().sync();

        } finally {

            workerGroup.shutdownGracefully();

            bossGroup.shutdownGracefully();

        }

    }

    public static class SampleInBoundHandler extends ChannelInboundHandlerAdapter {
        private final String name;
        private final boolean flush;

        public SampleInBoundHandler(String name, boolean flush) {
            this.name = name;
            this.flush = flush;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("InBoundHandler: " + name);
            if (flush) {
                ctx.channel().writeAndFlush(msg);
            } else {
//                super.channelRead(ctx, msg);

                throw new RuntimeException("InBoundHandler: " + name);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            System.out.println("InBoundHandlerException: " + name);
            ctx.fireExceptionCaught(cause);
        }
    }

    public static class SampleOutBoundHandler extends ChannelOutboundHandlerAdapter {
        private final String name;

        public SampleOutBoundHandler(String name) {
            this.name = name;
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutBoundHandler: " + name);
            super.write(ctx, msg, promise);
        }
    }

    public static class ExSampleOutBoundHandler extends ChannelOutboundHandlerAdapter {
        private final String name;

        public ExSampleOutBoundHandler(String name) {
            this.name = name;
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutBoundHandler: " + name);
            throw new RuntimeException("OutBoundHandler: " + name);

        }

    }

    public static class ExceptionHandler extends ChannelDuplexHandler {
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            if (cause instanceof RuntimeException) {
                System.out.println("Handle Business Exception Success.");
            }
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
            ctx.write(msg, promise.addListener((ChannelFutureListener) future -> {
                if (!future.isSuccess()) {
                    // Handle write exception here...
                    Throwable failureCause = future.cause();
                    System.out.println("outHandle Business Exception Success.");

                }
            }));
        }
    }
}
