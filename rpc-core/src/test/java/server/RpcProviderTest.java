package server;

import com.liu.server.RpcProvider;

/**
 * 服务端测试
 * @author knuslus
 */
public class RpcProviderTest {

    public static void main(String[] args) {
        FirstRpcServiceImpl service = new FirstRpcServiceImpl();
        RpcProvider rpcProvider = new RpcProvider();
        rpcProvider.register(service, 90);
    }
}
