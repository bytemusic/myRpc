package com.liu.rpc.core.client;

import com.liu.rpc.common.model.RpcRequest;

/**
 * @author knuslus
 */

public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}
