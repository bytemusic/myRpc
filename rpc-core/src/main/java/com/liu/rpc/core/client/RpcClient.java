package com.liu.rpc.core.client;

import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.common.model.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 向服务端发送请求
 *
 * @author knuslus
 */
public class RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    public Object sendRequest(RpcRequest rpcRequest, String host, int ip) {
        logger.info("RpcClient.sendRequest rpcRequest:{}, host:{} ip:{}", rpcRequest, host, ip);
        try(Socket socket = new Socket(host, ip)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //无法读取
            Object response = ((RpcResponse)objectInputStream.readObject()).getModel();
            return response;
        } catch (Exception e) {
            logger.warn("RpcClient.sendRequest warnning ", e);
            return null;
        }
    }
}
