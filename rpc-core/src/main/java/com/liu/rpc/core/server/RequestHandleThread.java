package com.liu.rpc.core.server;

import com.liu.rpc.common.model.RpcRequest;
import com.liu.rpc.common.model.RpcResponse;
import com.liu.rpc.core.serivce.ServiceRegister;
import com.liu.rpc.core.serivce.manager.RegisterManager;
import lombok.AllArgsConstructor;
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
public class RequestHandleThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandleThread.class);

    private Socket socket;

    private RequestHandle requestHandle;

    private ServiceRegister serviceRegister;

    public RequestHandleThread(Socket socket, RequestHandle requestHandle, ServiceRegister serviceRegister) {
        this.socket = socket;
        this.requestHandle = requestHandle;
        this.serviceRegister = serviceRegister;
    }

    //接收RpcRequest对象并回传RpcRespond对象
    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object service = serviceRegister.getService(rpcRequest.getInterfaceName());
            Object invoke = RequestHandle.handel(rpcRequest, service);
            objectOutputStream.writeObject(RpcResponse.success(invoke));
            objectOutputStream.flush();
        } catch (ClassNotFoundException | IOException e) {
            logger.warn("RequestHandleThread.run error",e);
            throw new RuntimeException(e);
        }
    }

}
