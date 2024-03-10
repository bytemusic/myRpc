package com.liu.rpc.common.model;

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
     * 返回码
     */
    private Integer code;

    /**
     * 返回码信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T model;


    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setModel(T model) {
        this.model = model;
    }


    public static <T> RpcResponse<T> success(T model) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setCode(200);
        rpcResponse.setMessage("请求成功");
        rpcResponse.setModel(model);
        return rpcResponse;
    }

    public static <T> RpcResponse<T> fail() {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setCode(500);
        rpcResponse.setMessage("请求失败");
        return rpcResponse;
    }

}
