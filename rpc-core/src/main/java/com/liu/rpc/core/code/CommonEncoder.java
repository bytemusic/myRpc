package com.liu.rpc.core.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CommonEncoder extends MessageToByteEncoder {
    private static final int MAGIC_NUMBER = 1;

    private final CommonSerializer commonSerializer;

    public CommonEncoder(CommonSerializer commonSerializer) {
        this.commonSerializer = commonSerializer;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

    }
}
