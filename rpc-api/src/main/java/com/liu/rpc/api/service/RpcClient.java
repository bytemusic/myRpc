package com.liu.rpc.api.service;

import com.liu.rpc.api.model.FirstRpcModel;

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

    /**
     * 根据订单号和商户id判断是否为医保订单
     * @param tradeId
     * @param sellerId
     * @return
     */
    String secondRpcMethod(String tradeId,long sellerId);
}
