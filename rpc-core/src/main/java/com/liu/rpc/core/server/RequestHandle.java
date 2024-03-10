package com.liu.rpc.core.server;

import com.liu.rpc.common.model.RpcRequest;
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
        Object invoke;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamType());
            invoke = method.invoke(service, rpcRequest.getParam());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.warn("RequestHandle.handle error e:", e);
            throw new RuntimeException(e);
        }
        return invoke;

    }
}
