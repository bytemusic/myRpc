package com.liu.rpc.api.service;

import com.liu.rpc.api.model.FirstRpcModel;

/**
 * @author knuslus
 */
public interface RpcClient {
    //客户端调用服务器代码
    String firstRpcMethod(FirstRpcModel firstRpcModel);
}
