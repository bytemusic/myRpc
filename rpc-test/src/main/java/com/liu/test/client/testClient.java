package com.liu.test.client;

import com.liu.model.FirstRpcModel;
import com.liu.proxy.RpcClientProxy;
import com.liu.service.RpcClient;
import com.liu.test.server.helloServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端测试
 * @author knuslus
 */
public class testClient {
    private static final Logger logger = LoggerFactory.getLogger(testClient.class);
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 90);
        RpcClient proxyClass = rpcClientProxy.getProxyClass(RpcClient.class);
        FirstRpcModel firstRpcModel = new FirstRpcModel(1, "发送第一条信息");
        logger.info("before testClient.proxyClass.firstRpcModel");
        String message = proxyClass.firstRpcMethod(firstRpcModel);
        logger.info("after testClient.proxyClass.firstRpcMethod");
        System.out.println(message);

    }
}
