package com.wickend.payload;

import lombok.Data;

/**
 * Created by vyl on 4/16/16.
 */
@Data
public class Courier {
    private Long id;
    private String name;
    private String description;

    public Courier() {}

    public Courier(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
