package com.wickend.cotroller;

import com.wickend.entity.Advocacy;
import com.wickend.entity.Candidate;
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

    @RequestMapping(value = "receipt/{id}")
    public Receipt receipt(@PathVariable("id") String id) {

        Map<Long, String> courier = new HashMap<>();
        courier.put(1L, "XYZ Boombastic");
        courier.put(2L, "Caterpilarpil Express");
        courier.put(3L, "Padalapit");
        courier.put(4L, "PikPakBoom Cour");
        courier.put(5L, "UpUpAndAway");

        Long cid = Long.valueOf(id);
        Result r = resultRepository.findOne(cid);
        Candidate cone = candidateRepository.findOne(r.getCandidateId());
        List<Product> products = new ArrayList<>();
        List<String> ac = Arrays.asList(r.getItems().split("\\|"));
        ac.forEach(s -> {
            Advocacy one = advocacyRepository.findOne(Long.valueOf(s));
            products.add(new Product(one.getId(), one.getTitle(), "img", one.getDescription()));
        });
        double totCount = resultRepository.count();
        final double[] ctr = {0};
        resultRepository.findAll().forEach(result -> {
            if (result.getCandidateId().equals(cone.getId())) ctr[0]++;
        });

        Receipt receipt = new Receipt();
        receipt.setChoosenAdvocacy(products);
        List<Product> mainAdvocacies = new ArrayList<>();
        cone.getCandidateAdvocacies().forEach(mainAdv -> {
            Advocacy adv = mainAdv.getAdvocacy();
            mainAdvocacies.add(new Product(adv.getId(), adv.getTitle(), "img", adv.getDescription()));
        });
        receipt.setMainAdvocacy(mainAdvocacies);
        receipt.setPresName(cone.getFirstName() + " " + cone.getLastName());
        receipt.setPercent(((ctr[0]/totCount) * 100));
        receipt.setCourier(courier.get(cone.getId()));
        receipt.setCriticism(cone.getCriticisms());
        receipt.setMunicipalVisitedPrcnt(cone.getMunVistdPercnt());

        return receipt;
    }

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
                    products.add(new Product(c.getAdvocacy().getId(), c.getAdvocacy().getTitle(), "img", c.getAdvocacy().getDescription()));
            }
        });

        return products;
    }

    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.POST, value = "get-couriers")
    public List<Courier> couriers(@RequestBody List<Long> products) {
        List<Courier> couriers = new ArrayList<>();

        CandAdvCtr binay = new CandAdvCtr(1L, 0, "XYZ Boombastic", candidateRepository.findOne(1L).getCriticisms());
        CandAdvCtr duterte = new CandAdvCtr(2L, 0, "Caterpilarpil Express", candidateRepository.findOne(2L).getCriticisms());
        CandAdvCtr poe = new CandAdvCtr(3L, 0, "Padalapit", candidateRepository.findOne(3L).getCriticisms());
        CandAdvCtr roxas = new CandAdvCtr(4L, 0, "PikPakBoom Cour", candidateRepository.findOne(4L).getCriticisms());
        CandAdvCtr santiago = new CandAdvCtr(5L, 0, "UpUpAndAway", candidateRepository.findOne(5L).getCriticisms());
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

        for (int x = 0; x < 3; x++) {
            Long id = candAdvCtrszzz.get(x).id;
            couriers.add(new Courier(id, candAdvCtrszzz.get(x).pseudoName, candAdvCtrszzz.get(x).criticism, getStand(id)));
        }

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

    class CandAdvCtr {
        public CandAdvCtr(Long id, Integer ctr, String pseudoName, String criticism) {
            this.id = id;
            this.ctr = ctr;
            this.pseudoName = pseudoName;
            this.criticism = criticism;
        }

        public Long id;
        public Integer ctr;
        public String pseudoName;
        public String criticism;

        @Override
        public String toString() {
            return "CandAdvCtr{" +
                    "id=" + id +
                    ", ctr=" + ctr +
                    ", pseudoName='" + pseudoName + '\'' +
                    '}';
        }
    }

    // TODO put to db
    private String getStand(Long id) {
        Map<Long, String> stand = new HashMap<>();
        stand.put(1L, "PASS FREEDOM OF INFORMATION BILL? - YES|" +
                "PASS ANTI-POLITICAL DYNASTY BILL? - NO|" +
                "LOWER PERSONAL INCOME TAX? - YES|" +
                "PASS PROPOSED BANGSAMORO BASIC LAW? - NO|" +
                "FIGHT FOR RIGHTS OVER WEST PHILIPPINE SEA? - YES|" +
                "PUSH FOR CHARTER CHANGE? - YES|" +
                "PROMOTE EQUAL RIGHTS FOR LGBTs? - YES|" +
                "CONTINUE CONDITIONAL CASH TRANSFER PROGRAM? - YES|" +
                "SUPPORT JOB CONTRACTUALIZATION? - NO|" +
                "PUSH FOR THE PASSAGE OF A DIVORCE BILL? - NO");
        stand.put(2L, "PASS FREEDOM OF INFORMATION BILL? - YES|" +
                "PASS ANTI-POLITICAL DYNASTY BILL? - NO|" +
                "LOWER PERSONAL INCOME TAX? - NO|" +
                "PASS PROPOSED BANGSAMORO BASIC LAW? - YES|" +
                "FIGHT FOR RIGHTS OVER WEST PHILIPPINE SEA? - YES|" +
                "PUSH FOR CHARTER CHANGE? - YES|" +
                "PROMOTE EQUAL RIGHTS FOR LGBTs? - YES|" +
                "CONTINUE CONDITIONAL CASH TRANSFER PROGRAM? - YES|" +
                "SUPPORT JOB CONTRACTUALIZATION? - NO|" +
                "PUSH FOR THE PASSAGE OF A DIVORCE BILL? - NO");
        stand.put(3L, "PASS FREEDOM OF INFORMATION BILL? - YES|" +
                "PASS ANTI-POLITICAL DYNASTY BILL? - YES|" +
                "LOWER PERSONAL INCOME TAX? - YES|" +
                "PASS PROPOSED BANGSAMORO BASIC LAW? - NO CLEAR STAND|" +
                "FIGHT FOR RIGHTS OVER WEST PHILIPPINE SEA? - YES|" +
                "PUSH FOR CHARTER CHANGE? - YES|" +
                "PROMOTE EQUAL RIGHTS FOR LGBTs? - YES|" +
                "CONTINUE CONDITIONAL CASH TRANSFER PROGRAM? - YES|" +
                "SUPPORT JOB CONTRACTUALIZATION? - NO|" +
                "PUSH FOR THE PASSAGE OF A DIVORCE BILL? - NO");
        stand.put(4L, "PASS FREEDOM OF INFORMATION BILL? - YES|" +
                "PASS ANTI-POLITICAL DYNASTY BILL? - YES|" +
                "LOWER PERSONAL INCOME TAX? - NO CLEAR STAND|" +
                "PASS PROPOSED BANGSAMORO BASIC LAW? - YES|" +
                "FIGHT FOR RIGHTS OVER WEST PHILIPPINE SEA? - YES|" +
                "PUSH FOR CHARTER CHANGE? - NO|" +
                "PROMOTE EQUAL RIGHTS FOR LGBTs? - YES|" +
                "CONTINUE CONDITIONAL CASH TRANSFER PROGRAM? - YES|" +
                "SUPPORT JOB CONTRACTUALIZATION? - NO|" +
                "PUSH FOR THE PASSAGE OF A DIVORCE BILL? - NO");
        stand.put(5L, "PASS FREEDOM OF INFORMATION BILL? - YES|" +
                "PASS ANTI-POLITICAL DYNASTY BILL? - YES|" +
                "LOWER PERSONAL INCOME TAX? - YES|" +
                "PASS PROPOSED BANGSAMORO BASIC LAW? - NO|" +
                "FIGHT FOR RIGHTS OVER WEST PHILIPPINE SEA? - YES|" +
                "PUSH FOR CHARTER CHANGE? - YES|" +
                "PROMOTE EQUAL RIGHTS FOR LGBTs? - YES|" +
                "CONTINUE CONDITIONAL CASH TRANSFER PROGRAM? - YES|" +
                "SUPPORT JOB CONTRACTUALIZATION? - NO|" +
                "PUSH FOR THE PASSAGE OF A DIVORCE BILL? - YES");

        return stand.get(id);
    }
}
