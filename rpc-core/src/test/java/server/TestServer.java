package server;

import com.liu.server.RpcProvider;

/**
 * 服务端测试
 * @author knuslus
 */
public class TestServer {

    public static void main(String[] args) {
        HelloServer server = new HelloServer();
        RpcProvider rpcProvider = new RpcProvider();
        rpcProvider.register(server, 90);
    }
}
