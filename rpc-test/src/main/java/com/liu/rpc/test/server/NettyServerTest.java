package com.liu.rpc.test.server;

import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.serivce.ServiceRegister;
import com.liu.rpc.core.serivce.manager.RegisterManager;
import com.liu.rpc.core.server.NettyServer;

/**
 * netty测试类
 * @author knuslus
 */
public class NettyServerTest {
    private static final Integer PORT = 8090;

    public static void main(String[] args) {
        RpcClient server = new HelloServer();
        ServiceRegister serviceRegister = new RegisterManager();
        serviceRegister.registerService(server);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(server, PORT);
    }
}
