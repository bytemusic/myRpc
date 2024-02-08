package com.liu.rpc.core.client;

import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.common.model.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author knuslus
 */
@AllArgsConstructor
public class NettyClient implements RpcClient{
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private String ip;
    private int port;
    private static final Bootstrap bootstrap = new Bootstrap();
    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        try {
            logger.info("NettyClient.sendRequest ip:{}. port:{}", ip, port);
            ChannelFuture connect = bootstrap.connect(ip, port).sync();
            Channel channel = connect.channel();
            if (channel != null) {
                channel.writeAndFlush(rpcRequest).addListener(future -> {
                    if (future.isSuccess()) {
                        logger.info("NettyClient.sendRequest exec success rpcRequest:{}, future:{}", rpcRequest, future);
                    } else {
                        logger.warn("NettyClient.sendRequest exec error", future.cause());
                    }
                });
                channel.closeFuture().sync();
                AttributeKey<RpcResponse> rpcRequestKey = AttributeKey.valueOf("rpcRequest");
                RpcResponse rpcResponse = channel.attr(rpcRequestKey).get();
                Object model = rpcResponse.getModel();
                return model;
            }
        } catch (InterruptedException e) {
            logger.warn("NettyClient.sendRequest exec error", e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
