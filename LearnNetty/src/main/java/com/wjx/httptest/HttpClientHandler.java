package com.wjx.httptest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;

/**
 * @Author WangJX
 * @Date 2022/11/20 15:25
 * @Description
 */
public class HttpClientHandler extends ChannelInboundHandlerAdapter {

    @Override

    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof HttpContent) {

            HttpContent content = (HttpContent) msg;

            ByteBuf buf = content.content();

            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));

            buf.release();

        }

    }

}

