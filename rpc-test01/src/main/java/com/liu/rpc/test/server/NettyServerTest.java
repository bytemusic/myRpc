package com.liu.rpc.test.server;

import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.serivce.ServiceProvider;
import com.liu.rpc.core.serivce.manager.ProviderManager;
import com.liu.rpc.core.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty测试类
 * @author knuslus
 */
public class NettyServerTest {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerTest.class);
    private static final Integer PORT = 8090;

    public static void main(String[] args) {
        RpcClient server = new HelloServer();
        ServiceProvider serviceProvider = new ProviderManager();
        serviceProvider.registerService(server);
        logger.info("注册服务");
        NettyServer nettyServer = new NettyServer();
        logger.info("启动服务");
        nettyServer.start(server, PORT);
    }
}
