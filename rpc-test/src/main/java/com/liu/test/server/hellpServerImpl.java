package com.liu.test.server;

import com.liu.model.FirstRpcModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class hellpServerImpl implements helloServer{
    private static final Logger logger = LoggerFactory.getLogger(hellpServerImpl.class);

    @Override
    public String firstRpcMethod(FirstRpcModel firstRpcModel) {
        logger.info("接收到服务{}", firstRpcModel.getMessage());
        return String.valueOf(firstRpcModel.getId());
    }
}
