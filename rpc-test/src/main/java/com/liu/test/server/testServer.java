package com.liu.test.server;

import com.liu.server.RpcServer;

/**
 * @author knuslus
 */
public class testServer {

    public static void main(String[] args) {
        helloServer server = new helloServer();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(server, 90);
    }
}
