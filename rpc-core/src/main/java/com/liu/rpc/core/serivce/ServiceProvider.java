package com.liu.rpc.core.serivce;


/**
 * @author knuslus
 */
public interface ServiceProvider {

    /**
     * 注册服务
     * @param service 服务名
     * @param <T> 泛型
     */
    <T> void registerService(T service);


    /**
     * 获取服务
     * @param serviceName 注册服务名
     * @return 服务名
     */
    Object getService(String serviceName);
}
