package com.wjx.multiprotocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class MyTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println(String.format("收到websocket客户端[%s]消息:", ctx.channel().remoteAddress()+":"+ctx.channel().id().asLongText()) + msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame(  msg.text()));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生:" + cause.getMessage());
        ctx.close();
    }
}
