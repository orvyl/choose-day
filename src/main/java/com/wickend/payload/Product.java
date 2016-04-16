package com.wickend.payload;

import lombok.Data;

/**
 * Created by vyl on 4/16/16.
 */
@Data
public class Product {
    private Long id;
    private String name;
    private String img;
    private String description;

    public Product(){}

    public Product(Long id, String name, String img, String description){
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
    }
}
