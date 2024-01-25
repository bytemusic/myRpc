package com.liu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 服务端返回参数
 *
 * @author knuslus
 */
@Data
@NoArgsConstructor
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

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setModel(T model) {
        this.model = model;
    }


    public static <T> RpcResponse<T> success(T model, long requestId) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(requestId);
        rpcResponse.setCode(200);
        rpcResponse.setMessage("请求成功");
        rpcResponse.setModel(model);
        return rpcResponse;
    }

    public static <T> RpcResponse<T> fail(long requestId) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setCode(500);
        rpcResponse.setMessage("请求失败");
        return rpcResponse;
    }

}
