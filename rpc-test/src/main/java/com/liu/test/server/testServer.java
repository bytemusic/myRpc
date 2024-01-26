package com.liu.test.server;

import com.liu.server.RpcServer;

/**
 * 服务端测试
 * @author knuslus
 */
public class testServer {

    public static void main(String[] args) {
        hellpServerImpl server = new hellpServerImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(server, 90);
    }
}
