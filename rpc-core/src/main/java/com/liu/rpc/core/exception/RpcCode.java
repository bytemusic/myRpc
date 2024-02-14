package com.liu.rpc.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * rpc code
 * @author knuslus
 */
@AllArgsConstructor
@Getter
public enum RpcCode {
    /**
     *
     */
    PARAM_ERROR(00001, "参数异常"),
    MAGIC_ERROR(00002,"魔术数错误"),

    SERIALIZER_ERROR(00003, "序列化失败")
    ;
    private int code;

    private  String desc;
}
