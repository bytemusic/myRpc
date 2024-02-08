package com.liu.rpc.test.server;

import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.serivce.ServiceRegister;
import com.liu.rpc.core.serivce.manager.RegisterManager;
import com.liu.rpc.core.server.SocketServer;

/**
 * 服务端测试
 * @author knuslus
 */
public class testServer {

    public static void main(String[] args) {
        RpcClient server = new helloServer();
        ServiceRegister serviceRegister = new RegisterManager();
        //注册服务
        serviceRegister.registerService(server);
        SocketServer socketServer = new SocketServer(serviceRegister);
        //启动服务
        socketServer.start(server, 90);
    }
}
