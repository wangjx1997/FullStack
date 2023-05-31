package com.wjx.httptest;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * @Author WangJX
 * @Date 2022/11/20 14:49
 * @Description
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {

        String content = String.format("Receive http request, uri: %s, method: %s, content: %s%n", msg.uri(), msg.method(), msg.content().toString(CharsetUtil.UTF_8));

        FullHttpResponse response = new DefaultFullHttpResponse(

                HttpVersion.HTTP_1_1,

                HttpResponseStatus.OK,

                Unpooled.wrappedBuffer(content.getBytes()));

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

}
