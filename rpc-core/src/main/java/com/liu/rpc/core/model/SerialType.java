package com.liu.rpc.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 序列化类型
 * @author knuslus
 */
@AllArgsConstructor
@Getter
public enum SerialType {

    /**
     * 序列化类型
     */
    NETTY(1, "netty序列化"),
    JACKSON(2, "jackson序列化");
    private int code;
    private String desc;

}
