package com.liu.service;

import com.liu.model.FirstRpcModel;

/**
 * 服务端向注册中心注册的服务，也是客户端需要调用的服务
 * @author knuslus
 */
public interface FirstRpcService {
    /**
     * 服务提供者的第一个方法
     * @param firstRpcModel 请求体
     * @return 返回参数
     */
    String firstRpcMethod(FirstRpcModel firstRpcModel);
}
