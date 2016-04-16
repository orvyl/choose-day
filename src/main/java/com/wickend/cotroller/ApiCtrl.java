package com.wickend.cotroller;

import com.wickend.payload.Courier;
import com.wickend.payload.Product;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@RestController
@RequestMapping("/")
public class ApiCtrl {

    @RequestMapping(value = "products")
    public List<Product> productsToDisplay() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "get-couriers")
    public List<Courier> couriers(@RequestBody List<Long> products) {
        return null;
    }
}
