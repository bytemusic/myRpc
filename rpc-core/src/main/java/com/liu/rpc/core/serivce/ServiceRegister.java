package com.liu.rpc.core.serivce;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * nacos的远程注册表
 * @author knuslus
 */
public interface ServiceRegister {
    /**
     * 将服务名称和服务地址注册到nacos注册中心
     * @param serviceName
     * @param address
     */
    void register(String serviceName, String address);

    /**
     * 根据服务名称获取服务地址
     * @param serviceName
     */
    List<Instance> lookService(String serviceName);

}
