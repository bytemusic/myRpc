package com.liu.rpc.core.serivce.manager;

import com.liu.rpc.core.serivce.ServiceRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author knuslus
 */
public class RegisterManager implements ServiceRegister {
    private static final Logger logger = LoggerFactory.getLogger(RegisterManager.class);
    //存放注册的服务容器
    Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    //可以把注册成功的服务保存在一个set集合里面，保证不重复
    Set<String> serviceSet = new HashSet<>();

    @Override
    public <T> void registerService(T service) {
        //注册服务，有服务ip,
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class<?> s : interfaces) {
            if (serviceMap.containsKey(s.getCanonicalName())) {
                return;
            } else {
                logger.info("{}注册服务", s);
                serviceMap.put(s.getCanonicalName(), service);
            }
        }
    }

    @Override
    public Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("服务不存在");
        }
        return service;
    }


}
