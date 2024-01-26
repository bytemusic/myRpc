package com.liu.test.client;

import com.liu.model.FirstRpcModel;
import com.liu.proxy.RpcClientProxy;
import com.liu.service.RpcClient;
import com.liu.test.server.helloServer;

/**
 * 客户端测试
 * @author knuslus
 */
public class testClient {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 90);
        RpcClient proxyClass = rpcClientProxy.getProxyClass(helloServer.class);
        FirstRpcModel FirstRpcModel = new FirstRpcModel(1, "发送第一条信息");
        String s = proxyClass.firstRpcMethod(FirstRpcModel);
        System.out.println(s);

    }
}
