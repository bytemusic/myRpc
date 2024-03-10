package com.liu.service;

import com.liu.model.FirstRpcModel;

/**
 * @author knuslus
 */
public interface RpcClient {
    /**
     * 客户端调用服务器代码
     * @param firstRpcModel
     * @return
     */
    String firstRpcMethod(FirstRpcModel firstRpcModel);
}
