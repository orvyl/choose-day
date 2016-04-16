package com.wickend.cotroller;

import com.wickend.payload.Courier;
import com.wickend.payload.Product;
import com.wickend.repo.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@RestController
@RequestMapping("/pres")
public class ApiPresCtrl {

    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping(value = "products")
    public List<Product> productsToDisplay() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Poverty reduction", "img", "Poverty reduction blabla"));
        products.add(new Product(2L, "Job Creation", "img", "Job creation blabla"));
        products.add(new Product(3L, "Protection and promotion of rights of OFWs", "img", "Protection and promotion of rights of OFWs blabla"));
        products.add(new Product(4L, "Welfare of senior citizens", "img", "Welfare of senior citizens blabla"));
        products.add(new Product(5L, "Increased benefits of public school teachers", "img", "Increased benefits of public school teachers  blabla"));
        products.add(new Product(6L, "Mass housing", "img", "Mass housing blabla"));

        return products;
    }

    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.POST, value = "get-couriers")
    public List<Courier> couriers(@RequestBody List<Long> products) {
        List<Courier> couriers = new ArrayList<>();
        couriers.add(new Courier(1L, "XY Boombastic", "Courier boom boom"));
        couriers.add(new Courier(2L, "Caterpillapilapil", "Courier boom boom"));
        couriers.add(new Courier(3L, "Padalapit", "Courier boom boom"));
        return couriers;
    }

    @RequestMapping(value = "courier/{id}", method = RequestMethod.POST)
    public String result(@RequestBody List<Long> products, @PathVariable("id") String courierId) {
        return "duterte";
    }
}
