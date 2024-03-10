package client;

import com.liu.rpc.api.model.FirstRpcModel;
import com.liu.rpc.api.service.RpcClient;
import com.liu.rpc.core.client.NettyClient;
import com.liu.rpc.core.proxy.RpcClientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NettyClientTest
 * @author knuslus
 */
public class NettyClientTest {
    private static final Logger logger = LoggerFactory.getLogger(NettyClientTest.class);
    private static final String IP = "127.0.0.1";
    private static final Integer PORT = 8090;

    public static void main(String[] args) {

        NettyClient nettyClient = new NettyClient(IP, PORT);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(nettyClient);
        com.liu.rpc.api.service.RpcClient proxyClass = rpcClientProxy.getProxyClass(RpcClient.class);
        FirstRpcModel firstRpcModel= new FirstRpcModel();
        firstRpcModel.setId(000001);
        firstRpcModel.setMessage("netty消息");
        logger.info("NettyClientTest start......");
        String messageId = proxyClass.firstRpcMethod(firstRpcModel);
        logger.info("message id {}", messageId);
    }
}
