package com.liu.rpc.test.server;

import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.serivce.ServiceProvider;
import com.liu.rpc.core.serivce.manager.ProviderManager;
import com.liu.rpc.core.server.SocketServer;

/**
 * 服务端测试
 * @author knuslus
 */
public class ServerTest {

    public static void main(String[] args) {
        RpcClient server = new HelloServer();
        ServiceProvider serviceProvider = new ProviderManager();
        //注册服务
        serviceProvider.registerService(server);
        SocketServer socketServer = new SocketServer(serviceProvider);
        //启动服务
        socketServer.start(server, 90);
    }
}
