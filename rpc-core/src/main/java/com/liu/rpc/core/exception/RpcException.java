package com.liu.rpc.core.exception;

/**
 * 异常类
 * @author knuslus
 */
public class RpcException extends RuntimeException{
    private RpcCode code;

    private String desc;

    public RpcException(RpcCode code, String desc) {
        super(code.getCode() + ":" + desc);
    }


}
