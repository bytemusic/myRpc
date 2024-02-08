package com.liu.rpc.core.proxy;

import com.liu.rpc.core.client.SocketClient;
import com.liu.rpc.common.model.RpcRequest;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author knuslus
 * 动态代理客户端类由于向服务端发起请求
 * 代理实现接口的类使用jdk代理
 */
@AllArgsConstructor
public class RpcClientProxy implements InvocationHandler {

    private String ip;

    private int port;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行逻辑，生成一个rpcClient对象，向服务端发送请求，
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .param(args)
                .build();
        //用RpcClient发送请求
        SocketClient rpcClient = new SocketClient(ip, port);
        return rpcClient.sendRequest(rpcRequest);
    }

    /**
     * 生成代理对象
     * @param clazz 需要代理的对象
     * @return 代理对象
     * @param <T> 泛型
     */
    @SuppressWarnings("unchecked")
    public <T> T getProxyClass(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }
}