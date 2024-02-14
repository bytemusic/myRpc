package com.liu.rpc.core.serivce;


/**
 * @author knuslus
 */
public interface ServiceRegister {

    //注册服务
    <T> void registerService(T service);

    //获取服务
    Object getService(String serviceName);
}
