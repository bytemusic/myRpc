package com.liu.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author knuslus
 */
@Data
@AllArgsConstructor
public class firstRpc implements Serializable {
    /**
     *
     */
    private int id;

    /**
     *
     */
    private String message;
}
