package server;

import com.liu.server.RpcServer;

/**
 * 服务端测试
 * @author knuslus
 */
public class TestServer {

    public static void main(String[] args) {
        HelloServer server = new HelloServer();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(server, 90);
    }
}
