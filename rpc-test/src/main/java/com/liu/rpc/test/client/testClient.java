package com.liu.rpc.test.client;

import com.liu.rpc.api.model.FirstRpcModel;
import com.liu.rpc.core.proxy.RpcClientProxy;
import com.liu.rpc.api.service.RpcClient;

/**
 * 客户端测试
 * @author knuslus
 */
public class testClient {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 90);
        RpcClient proxyClass = rpcClientProxy.getProxyClass(RpcClient.class);
        FirstRpcModel FirstRpcModel = new FirstRpcModel(1, "发送第一条信息");
        String s = proxyClass.firstRpcMethod(FirstRpcModel);
        System.out.println(s);

    }
}
