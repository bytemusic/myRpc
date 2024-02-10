package com.liu.rpc.test.client;

import com.liu.rpc.api.model.FirstRpcModel;
import com.liu.rpc.core.client.NettyClient;
import com.liu.rpc.core.client.RpcClient;
import com.liu.rpc.core.proxy.RpcClientProxy;
import com.liu.rpc.test.server.HelloServer;
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

        RpcClient nettyClient = new NettyClient(IP, PORT);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(IP, PORT);
        HelloServer proxyClass = rpcClientProxy.getProxyClass(HelloServer.class);
        FirstRpcModel firstRpcModel= new FirstRpcModel();
        firstRpcModel.setId(000001);
        firstRpcModel.setMessage("netty消息");
        String messageId = proxyClass.firstRpcMethod(firstRpcModel);
        logger.info("message id {}", messageId);

    }
}
