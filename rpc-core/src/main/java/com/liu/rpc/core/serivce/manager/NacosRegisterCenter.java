package com.liu.rpc.core.serivce.manager;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import com.liu.rpc.core.serivce.ServiceRegister;
import jdk.jfr.internal.instrument.ThrowableTracer;

import java.util.List;


/**
 *
 * @author knuslus
 */
public class NacosRegisterCenter implements ServiceRegister {

    private static final String SERVER_ADDRESS = "127.0.0.1:8848";

    private NacosNamingService nacosNamingService;

    private static NamingService namingService;

    static {
        try {
            //通过NamingFactory创建NamingService的方式连接nacos
            namingService = NamingFactory.createNamingService(SERVER_ADDRESS);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * namingService 提供了两个很方便的接口，registerInstance 和 getAllInstances 方法，
     * 前者可以直接向 Nacos 注册服务，后者可以获得提供某个服务的所有提供者的列表
     */
    @Override
    public void register(String serviceName, String address) {
        try {
            String[] split = SERVER_ADDRESS.split(":");
            namingService.registerInstance(serviceName, split[0], Integer.valueOf(split[1]));
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Instance> lookService(String serviceName) {
        try {
            List<Instance> allInstances = namingService.getAllInstances(serviceName);
            return allInstances;
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}
