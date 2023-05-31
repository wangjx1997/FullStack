package com.wjx.affinitytest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import net.openhft.affinity.AffinityStrategies;
import net.openhft.affinity.AffinityThreadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @Author WangJX
 * @Date 2023/2/14 9:47
 * @Description
 */
public class AffinityTest {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);

        ThreadFactory threadFactory = new AffinityThreadFactory("worker", AffinityStrategies.DIFFERENT_CORE);

        EventLoopGroup workerGroup = new NioEventLoopGroup(4, threadFactory);

        ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup);

    }
}
