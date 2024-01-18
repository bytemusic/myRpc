package com.liu.test.client;

import com.liu.model.firstRpc;
import com.liu.proxy.RpcClientProxy;
import com.liu.service.rpcClient;
import com.liu.test.server.helloServer;

public class testClient {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 90);
        rpcClient proxyClass = rpcClientProxy.getProxyClass(helloServer.class);
        firstRpc firstRpc = new firstRpc(1, "发送第一条信息");
        String s = proxyClass.firstRpcMethod(firstRpc);
        System.out.println(s);

    }
}
