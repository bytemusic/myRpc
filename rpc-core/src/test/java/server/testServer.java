package server;

import com.liu.server.RpcServer;

/**
 * 服务端测试
 * @author knuslus
 */
public class testServer {

    public static void main(String[] args) {
        helloServer server = new helloServer();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(server, 90);
    }
}
