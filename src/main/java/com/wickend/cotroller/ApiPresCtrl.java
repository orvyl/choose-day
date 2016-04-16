package com.wickend.cotroller;

import com.wickend.entity.CandidateAdvocacy;
import com.wickend.payload.Courier;
import com.wickend.payload.Product;
import com.wickend.repo.AdvocacyRepository;
import com.wickend.repo.CandidateAdvocacyRepository;
import com.wickend.repo.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@RestController
@RequestMapping("/pres")
public class ApiPresCtrl {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateAdvocacyRepository candidateAdvocacyRepository;

    @Autowired
    private AdvocacyRepository advocacyRepository;

    @RequestMapping(value = "products")
    public List<Product> productsToDisplay() {
        List<Product> products = new ArrayList<>();
        candidateRepository.findAll().forEach(candidate -> {
            List<CandidateAdvocacy> candidateAdvocacies = candidate.getCandidateAdvocacies();
            Collections.shuffle(candidateAdvocacies);
            for (int x = 0; x < 3; x++) {
                CandidateAdvocacy c = candidateAdvocacies.get(x);
                products.add(new Product(c.getId(), c.getAdvocacy().getTitle(), "img", c.getAdvocacy().getDescription()));
            }
        });

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
