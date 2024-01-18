package com.liu.serviceimpl;

import com.liu.model.firstRpc;
import com.liu.service.rpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2024-01-07
 * @author knuslus
 */
public class rpcClientImpl implements rpcClient {
    private static final Logger logger = LoggerFactory.getLogger(rpcClientImpl.class);
    @Override
    public String firstRpcMethod(firstRpc firstRpc) {
        //服务端对接口进行实现
        logger.info("返回数据{}", firstRpc.getMessage());
        return "第一个简单rpc远程方法";
    }
}
