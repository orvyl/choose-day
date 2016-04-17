package com.wickend.payload;

import lombok.Data;

import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@Data
public class Receipt {
    private String presName;
    private String courier;
    private List<Product> choosenAdvocacy;
    private List<Product> mainAdvocacy;
    private double percent;
    private String municipalVisitedPrcnt;
    private String criticism;
}
