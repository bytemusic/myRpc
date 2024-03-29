package com.liu.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 定义传输的内容 网络传输需要进行序列化
 * 将请求方法，实体类放入请求体中
 * @author knuslus
 */
@Data
@Builder
public class RpcRequest<T> implements Serializable {
    /**
     * 服务器ip
     */
    private String ip;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     *方法名
     */
    private String methodName;

    /**
     * 参数
     */
    private Object[] param;

    /**
     * 参数类型
     */
    private Class<?>[] paramType;


}
