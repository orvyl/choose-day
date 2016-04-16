package com.wickend.payload;

import lombok.Data;

import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@Data
public class Receipt {
    private String presName;
    private List<Product> products;
    private Integer percent;
}
