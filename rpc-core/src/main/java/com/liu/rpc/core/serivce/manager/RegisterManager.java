package com.liu.rpc.core.serivce.manager;

import com.liu.rpc.core.serivce.ServiceRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册多个服务具体实现
 * @author knuslus
 */
public class RegisterManager implements ServiceRegister {
    private static final Logger logger = LoggerFactory.getLogger(RegisterManager.class);

    /**
     * 存放注册的服务容器，可能有多个服务同时注册
     */
    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    /**
     * 可以把注册成功的服务保存在一个set集合里面，保证不重复
     */
    private final Set<String> serviceSet = ConcurrentHashMap.newKeySet();

    @Override
    public synchronized <T> void registerService(T service) {
        //注册服务，有服务ip,
        String canonicalName = service.getClass().getCanonicalName();
        //如果已经注册过，直接返回
        if (serviceSet.contains(canonicalName))
            return;
        //不存在再放入
        serviceSet.add(canonicalName);
        //接口列表？
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if (interfaces.length == 0) {
            throw new RuntimeException("interfaces.length == 0");
        }
        for (Class<?> serviceName : interfaces) {
            serviceMap.put(serviceName.getCanonicalName(), service);
        }
    }

    @Override
    public synchronized Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("服务不存在");
        }
        return service;
    }


}
