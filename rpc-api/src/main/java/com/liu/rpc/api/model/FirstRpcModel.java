package com.liu.rpc.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
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
