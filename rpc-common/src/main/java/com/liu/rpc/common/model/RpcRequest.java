package com.liu.rpc.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 定义传输的内容 网络传输需要进行序列化
 * @author knuslus
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Class<T> paramType;


}
