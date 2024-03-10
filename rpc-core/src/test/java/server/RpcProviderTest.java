package server;

import com.liu.server.RpcProvider;

/**
 * 服务端测试
 * @author knuslus
 */
public class RpcProviderTest {

    public static void main(String[] args) {
        FirstRpcServiceImpl server = new FirstRpcServiceImpl();
        RpcProvider rpcProvider = new RpcProvider();
        rpcProvider.register(server, 90);
    }
}
