package com.liu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用于服务消费者
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
     * 请求信息
     */
    private String message;

    public FirstRpcModel(int id, String message) {
        this.id = id;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

}
