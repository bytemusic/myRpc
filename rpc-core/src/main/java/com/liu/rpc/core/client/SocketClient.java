package com.liu.rpc.core.client;

import com.liu.rpc.common.model.RpcRequest;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SocketClient implements RpcClient{
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private String host;
    private int port;
    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        logger.info("RpcClient.sendRequest rpcRequest:{}, host:{} ip:{}", rpcRequest, host, port);
        try(Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();
        } catch (Exception e) {
            logger.warn("RpcClient.sendRequest warnning", e);
            return new Object();
        }
    }
}
