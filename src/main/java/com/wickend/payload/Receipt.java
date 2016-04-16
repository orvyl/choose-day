package com.wickend.payload;

import com.wickend.entity.Advocacy;
import com.wickend.entity.Candidate;
import lombok.Data;

import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@Data
public class Receipt {
    private Candidate candidate;
    private List<Advocacy> advocacies;
    private Integer percent;

    public Receipt() {}
    public Receipt(Candidate candidate, List<Advocacy> advocacies, Integer percent) {
        this.candidate = candidate;
        this.advocacies = advocacies;
        this.percent = percent;
    }
}
