package com.liu.rpc.core.serivce;


/**
 * 服务注册表，多个服务注册
 * @author knuslus
 */
public interface ServiceRegister {

    /**
     * 注册服务
     * @param service
     * @param <T>
     */
    <T> void registerService(T service);

    /**
     * 获取服务
     * @param serviceName
     * @return
     */
    Object getService(String serviceName);
}
