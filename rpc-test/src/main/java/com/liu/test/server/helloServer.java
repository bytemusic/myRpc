package com.liu.test.server;

import com.liu.model.firstRpc;
import com.liu.service.rpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author knuslus
 */
public class helloServer implements rpcClient {

    private static final Logger logger = LoggerFactory.getLogger(helloServer.class);
    @Override
    public String firstRpcMethod(firstRpc firstRpc) {
        logger.info("接收到服务{}", firstRpc.getMessage());
        return String.valueOf(firstRpc.getId());
    }
}
