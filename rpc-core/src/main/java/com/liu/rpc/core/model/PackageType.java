package com.liu.rpc.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 包类型
 * @author knuslus
 */
@AllArgsConstructor
@Getter
public enum PackageType {

    /**
     * 包类型，请求，响应
     */
    REQUEST(1, "请求"),
    RESPONSE(2, "响应");


    private int code;
    private String Type;
}
