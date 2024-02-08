package com.liu.rpc.core.server;

/**
 * @author knuslus
 */
public interface RpcServer {
    void start(Object service, int port);
}
