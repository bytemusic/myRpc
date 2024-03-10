package com.liu.service;

import com.liu.model.FirstRpcModel;

/**
 * 请求方法
 * @author knuslus
 */
public interface RpcConsumer {
    /**
     * 客户端调用服务器代码
     * @param firstRpcModel 请求体
     * @return 返回参数
     */
    String firstRpcMethod(FirstRpcModel firstRpcModel);
}
