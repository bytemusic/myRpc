package server;

import com.liu.rpc.api.service.PartnerService;
import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.serivce.ServiceRegister;
import com.liu.rpc.core.serivce.manager.RegisterManager;
import com.liu.rpc.core.server.RpcServer;

/**
 * 服务端测试
 * @author knuslus
 */
public class testServer {

    public static void main(String[] args) {
        RpcClient server = new helloServer();
        PartnerService partnerService = new PartnerServiceImpl();
        ServiceRegister serviceRegister = new RegisterManager();
        //注册服务
        serviceRegister.registerService(server);
        serviceRegister.registerService(partnerService);
        RpcServer rpcServer = new RpcServer(serviceRegister);
        //启动服务
        rpcServer.start(90);
    }
}
