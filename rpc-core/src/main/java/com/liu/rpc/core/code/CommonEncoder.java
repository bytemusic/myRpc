package com.liu.rpc.core.code;

import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.common.model.RpcResponse;
import com.liu.rpc.core.model.PackageType;
import com.liu.rpc.core.model.SerialType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码拦截器
 * 发送数据时编码
 */
public class CommonEncoder extends MessageToByteEncoder {
    private static final int MAGIC_NUMBER = 1;

    private final CommonSerializer commonSerializer;

    public CommonEncoder(CommonSerializer commonSerializer) {
        this.commonSerializer = commonSerializer;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) throws Exception {
        //byteBuf对数据进行操作
        byteBuf.writeInt(MAGIC_NUMBER);
        if (object instanceof RpcRequest) {
            byteBuf.writeInt(PackageType.REQUEST.getCode());
        } else if (object instanceof RpcResponse) {
            byteBuf.writeInt(PackageType.RESPONSE.getCode());
        }
        byteBuf.writeInt(SerialType.NETTY.getCode());
        byte[] bytes = commonSerializer.serializer(object);
        int length = bytes.length;
        byteBuf.writeInt(length);
        byteBuf.writeBytes(bytes);
    }
}
