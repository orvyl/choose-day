package com.wickend.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@Data
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Long id;

    private String firstName;
    private String lastName;
    private BigDecimal netWorth;

    @OneToMany(mappedBy = "candidate")
    private List<CandidateAdvocacy> candidateAdvocacies;

    public Candidate() {}
    public Candidate(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
