package com.liu.service;

import com.liu.model.firstRpc;

/**
 * @author knuslus
 */
public interface rpcClient {
    //客户端调用服务器代码
    String firstRpcMethod(firstRpc firstRpc);
}
