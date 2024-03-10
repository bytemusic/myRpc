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
 *
 * @author knuslus
 */
@AllArgsConstructor
@NoArgsConstructor
public class WorkThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(WorkThread.class);

    private Object service;

    private Socket socket;


    /**
     * 线程自动执行方法？
     * 接收RpcRequest对象并回传RpcRespond对象
     */
    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())){
            try {
                RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
                Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamType());
                Object invoke = method.invoke(service, rpcRequest.getParam());
                objectOutputStream.writeObject(RpcResponse.success(invoke, 2342L));
                objectOutputStream.flush();
            } catch (ClassNotFoundException e) {
                logger.warn("处理请求方法失败");
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                logger.warn("处理请求方法失败");
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                logger.warn("处理请求方法失败");
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                logger.warn("处理请求方法失败");
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
