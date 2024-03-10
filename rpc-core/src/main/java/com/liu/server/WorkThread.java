package com.liu.server;

import com.liu.model.RpcRequest;
import com.liu.model.RpcResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author knuslus
 */
@AllArgsConstructor
@NoArgsConstructor
public class WorkThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(WorkThread.class);

    private Object service;

    private Socket socket;


    /**
     * 线程自动执行方法
     * 接收RpcRequest对象并回传RpcRespond对象
     */
    @Override
    public void run() {
        try (
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())
             ) {ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamType());
            Object invoke = method.invoke(service, rpcRequest.getParam());
            //原来把返回结果封装成RpcResponse，服务端返回数据，客户端读取数据报错
            objectOutputStream.writeObject(invoke);
            objectOutputStream.flush();

        } catch (ClassNotFoundException | IOException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            logger.warn("处理请求方法失败", e);
            throw new RuntimeException(e);
        }
    }
}
