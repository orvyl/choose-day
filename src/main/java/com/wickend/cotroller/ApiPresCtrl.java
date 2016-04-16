package com.wickend.cotroller;

import com.wickend.entity.Advocacy;
import com.wickend.entity.CandidateAdvocacy;
import com.wickend.entity.Result;
import com.wickend.payload.Courier;
import com.wickend.payload.Product;
import com.wickend.payload.Receipt;
import com.wickend.repo.AdvocacyRepository;
import com.wickend.repo.CandidateAdvocacyRepository;
import com.wickend.repo.CandidateRepository;
import com.wickend.repo.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private ResultRepository resultRepository;

    @RequestMapping(value = "products")
    public List<Product> productsToDisplay() {
        List<Product> products = new ArrayList<>();
        candidateRepository.findAll().forEach(candidate -> {
            List<String> productsAdded = new ArrayList<>();
            products.forEach(product -> productsAdded.add(product.getName()));

            List<CandidateAdvocacy> candidateAdvocacies = candidate.getCandidateAdvocacies();
            Collections.shuffle(candidateAdvocacies);
            for (int x = 0; x < 3; x++) {
                CandidateAdvocacy c = candidateAdvocacies.get(x);
                if (!productsAdded.contains(c.getAdvocacy().getTitle()))
                    products.add(new Product(c.getId(), c.getAdvocacy().getTitle(), "img", c.getAdvocacy().getDescription()));
            }
        });

        return products;
    }

    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.POST, value = "get-couriers")
    public List<Courier> couriers(@RequestBody List<Long> products) {
        List<Courier> couriers = new ArrayList<>();

        CandAdvCtr binay = new CandAdvCtr(1L, 0, "XYZ Boombastic");
        CandAdvCtr duterte = new CandAdvCtr(2L, 0, "Caterpilarpil Express");
        CandAdvCtr poe = new CandAdvCtr(3L, 0, "Padalapit");
        CandAdvCtr roxas = new CandAdvCtr(4L, 0, "PikPakBoom Cour");
        CandAdvCtr santiago = new CandAdvCtr(5L, 0, "UpUpAndAway");
        Map<Long, CandAdvCtr> candsAdvsCtrs = new HashMap<>();
        candsAdvsCtrs.put(binay.id, binay);
        candsAdvsCtrs.put(duterte.id, duterte);
        candsAdvsCtrs.put(poe.id, poe);
        candsAdvsCtrs.put(roxas.id, roxas);
        candsAdvsCtrs.put(santiago.id, santiago);

        List<CandAdvCtr> candAdvCtrszzz = new ArrayList<>();
        candAdvCtrszzz.add(binay);
        candAdvCtrszzz.add(duterte);
        candAdvCtrszzz.add(poe);
        candAdvCtrszzz.add(roxas);
        candAdvCtrszzz.add(santiago);

        products.forEach(aLong -> {
            List<CandidateAdvocacy> ca = advocacyRepository.findOne(aLong).getCandidateAdvocacies();
            ca.forEach(candidateAdvocacy -> {
                Long id = candidateAdvocacy.getCandidate().getId();
                CandAdvCtr candAdvCtr = candsAdvsCtrs.get(id);
                candAdvCtr.ctr++;
            });
        });
        Collections.sort(candAdvCtrszzz, (o1, o2) -> o2.ctr - o1.ctr);

        for (int x = 0; x < 3; x++)
            couriers.add(new Courier(candAdvCtrszzz.get(x).id, candAdvCtrszzz.get(x).pseudoName, "[description]"));

        return couriers;
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "courier/{id}", method = RequestMethod.POST)
    public Long result(@RequestBody List<Long> products, @PathVariable("id") Long courierId) {
        StringBuilder stringBuilder  = new StringBuilder();
        products.forEach(aLong -> stringBuilder.append(aLong).append("|"));
        Result result = resultRepository.save(new Result(courierId, stringBuilder.toString()));
        return result.getId();
    }

    @RequestMapping(value = "receipt/{id}")
    public Receipt receipt(@PathVariable("id") String id) {
        Result r = resultRepository.findOne(Long.valueOf(id));
        List<Advocacy> advocacies = new ArrayList<>();
        return new Receipt(candidateRepository.findOne(r.getCandidateId()), advocacies, 89);
    }

    class CandAdvCtr {
        public CandAdvCtr(Long id, Integer ctr, String pseudoName) {
            this.id = id;
            this.ctr = ctr;
            this.pseudoName = pseudoName;
        }

        public Long id;
        public Integer ctr;
        public String pseudoName;

        @Override
        public String toString() {
            return "CandAdvCtr{" +
                    "id=" + id +
                    ", ctr=" + ctr +
                    ", pseudoName='" + pseudoName + '\'' +
                    '}';
        }
    }
}
