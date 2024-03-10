package com.liu.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请求方法的实体类
 * @author knuslus
 */
@Data
@NoArgsConstructor
public class FirstRpcModel implements Serializable {
    /**
     * 请求id
     */
    private int id;

    /**
     * 请求网站的URL
     */
    private String url;

    public FirstRpcModel(int id, String url) {
        this.id = id;
        this.url = url;
    }
    public String getMessage() {
        return url;
    }

    public int getId() {
        return id;
    }

}
