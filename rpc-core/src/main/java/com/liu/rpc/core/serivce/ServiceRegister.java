package com.liu.rpc.core.serivce;


/**
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
