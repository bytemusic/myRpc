package com.liu.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务端返回参数
 * @author knuslus
 */
@Data
public class RpcResponse<T> implements Serializable {
    /**
     * 请求id
     */
    private long requestId;
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回码信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T model;

    public static <T> RpcResponse<T> Success(T model, long requestId) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(requestId);
        rpcResponse.setCode(200);
        rpcResponse.setMessage("请求成功");
        rpcResponse.setModel(model);
        return rpcResponse;
    }

    public static <T> RpcResponse<T> Fail(long requestId) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setCode(500);
        rpcResponse.setMessage("请求失败");
        return rpcResponse;
    }

}
