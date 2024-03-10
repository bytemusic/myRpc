package com.liu.rpc.core.server;

import com.liu.model.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author knuslus
 */
public class RequestHandle {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandle.class);

    //通过代理调用的方法抽取出来
    public static Object handel(RpcRequest rpcRequest, Object service) {
        Method method = null;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamType());
        } catch (NoSuchMethodException e) {
            logger.warn("RequestHandle.handle error e:{}", e.getMessage());
            throw new RuntimeException(e);
        }
        Object invoke = null;
        try {
            invoke = method.invoke(service, rpcRequest.getParam());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return invoke;

    }
}
