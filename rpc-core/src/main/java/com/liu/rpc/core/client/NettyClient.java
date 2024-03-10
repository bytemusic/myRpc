package com.liu.rpc.core.client;

import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.common.model.RpcResponse;
import com.liu.rpc.core.code.CommonDecoder;
import com.liu.rpc.core.code.CommonEncoder;
import com.liu.rpc.core.code.CommonSerializerImpl;
import com.liu.rpc.core.handle.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author knuslus
 */
@AllArgsConstructor
@NoArgsConstructor
public class NettyClient implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private String ip;
    private int port;
    private static final Bootstrap bootstrap = new Bootstrap();

    static {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new CommonDecoder())
                                .addLast(new CommonEncoder(new CommonSerializerImpl()))
                                .addLast(new NettyClientHandler());
                    }
                });


    }

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
