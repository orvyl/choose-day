package com.wickend.payload;

import lombok.Data;

import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@Data
public class Courier {
    private Long id;
    private String name;
    private List<String> description;
}
