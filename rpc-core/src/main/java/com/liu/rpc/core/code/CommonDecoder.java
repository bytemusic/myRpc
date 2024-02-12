package com.liu.rpc.core.code;

import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.common.model.RpcResponse;
import com.liu.rpc.core.exception.RpcCode;
import com.liu.rpc.core.exception.RpcException;
import com.liu.rpc.core.model.PackageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码拦截器
 * 接收到数据时解码
 * 又可继承ReplayingDecoder
 */
public class CommonDecoder extends ByteToMessageDecoder {
    private CommonSerializer commonSerializer;
    private static final int MAGIC_NUMBER = 1;
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int magicNumber = byteBuf.readInt();
        if (magicNumber != MAGIC_NUMBER) {
            throw new RpcException(RpcCode.MAGIC_ERROR, RpcCode.MAGIC_ERROR.getDesc());
        }

        int packageType = byteBuf.readInt();
        Class<?> packageClass = null;
        if (packageType == PackageType.REQUEST.getCode()) {
            packageClass = RpcRequest.class;
        } else if (packageType == PackageType.RESPONSE.getCode()) {
            packageClass = RpcResponse.class;
        }

        int serializerCode = byteBuf.readInt();

        int dataLength = byteBuf.readInt();

        byte[] bytes = new byte[dataLength];
        byteBuf.readBytes(byteBuf);

        Object object = commonSerializer.deSerializer(bytes, packageClass);

        list.add(object);

    }

}
