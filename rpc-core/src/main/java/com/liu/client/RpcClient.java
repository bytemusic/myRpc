package com.liu.client;

import com.liu.model.RpcRequest;
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

    public Object sendRequest(RpcRequest rpcRequest, String host, int port) {
        logger.info("RpcClient.sendRequest rpcRequest:{}, host:{} ip:{}", rpcRequest, host, port);
        try(Socket socket = new Socket(host, port)) {
            // 获取输出流，并使用 ObjectOutputStream 将对象序列化发送给服务端
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush(); // 确保将数据发送给服务端

            // 获取输入流，并使用 ObjectInputStream 读取服务端的响应
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();

            // 返回服务端的响应
            return response;
        } catch (Exception e) {
            // 发生异常时，记录警告信息并返回 null
            logger.warn("RpcClient.sendRequest warning: {}", e.getMessage(), e);
            return null;
        }
    }
}
