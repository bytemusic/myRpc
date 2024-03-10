package com.liu.rpc.api.service;


/**
 * @author knuslus
 */
public interface RpcClient {

    /**
     * 客户端调用服务器代码
     * @param firstRpcModel
     * @return
     */
    String firstRpcMethod(com.liu.model.FirstRpcModel firstRpcModel);
}
