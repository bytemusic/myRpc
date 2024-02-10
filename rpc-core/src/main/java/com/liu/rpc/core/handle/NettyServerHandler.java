package com.liu.rpc.core.handle;

import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.core.serivce.ServiceRegister;
import com.liu.rpc.core.server.RequestHandle;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * 处理RpcRequest,并把处理结果封装成RpcResponse
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private static ServiceRegister serviceRegister;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {
        try {
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegister.getService(interfaceName);
            if (service != null) {
                //对对象进行动态代理，返回处理结果
                Object handel = RequestHandle.handel(rpcRequest, service);
                ChannelFuture channelFuture = channelHandlerContext.writeAndFlush(handel);
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        } finally {
            ReferenceCountUtil.release(rpcRequest);
        }


    }
}
