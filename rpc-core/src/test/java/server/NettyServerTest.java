package server;

import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.serivce.ServiceRegister;
import com.liu.rpc.core.serivce.manager.RegisterManager;
import com.liu.rpc.core.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty测试类
 * @author knuslus
 */
public class NettyServerTest {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerTest.class);
    private static final Integer PORT = 8090;

    public static void main(String[] args) {
        RpcClient server = new HelloServer();
        ServiceRegister serviceRegister = new RegisterManager();
        serviceRegister.registerService(server);
        logger.info("注册服务");
        NettyServer nettyServer = new NettyServer();
        logger.info("启动服务");
        nettyServer.start(server, PORT);
    }
}
