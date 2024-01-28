package com.liu.rpc.core.serivce;

import org.springframework.stereotype.Service;

/**
 * @author knuslus
 */
public interface ServiceRegister {

    //注册服务
    <T> void registerService(T service);

    //获取服务
    Object getService(String serviceName);
}
