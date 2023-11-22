package com.wjx.multiprotocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class CustomDecoder extends LengthFieldBasedFrameDecoder {
    

    public CustomDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf byteBuf = (ByteBuf) super.decode(ctx, in);
        if (byteBuf == null) {
            return null;
        }
        SimpleProtocol simpleProtocol = new SimpleProtocol();
        simpleProtocol.setProtocolType(byteBuf.readByte());
        int length = byteBuf.readInt();
        simpleProtocol.setBodyLength(length);
        if (simpleProtocol.getBodyLength() > 0) {
            byte[] body = new byte[length];
            byteBuf.readBytes(body);
            simpleProtocol.setBody(body);
        }
        in.release();//记得释放
        return simpleProtocol;
    }

}
