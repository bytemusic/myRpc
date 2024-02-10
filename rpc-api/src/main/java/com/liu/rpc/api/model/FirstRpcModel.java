package com.liu.rpc.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author knuslus
 */
@Data
@NoArgsConstructor
public class FirstRpcModel implements Serializable {
    /**
     *
     */
    private int id;

    /**
     *
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
