package com.wickend.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@Data
@Entity
public class Candidate {
    @Id
    @Setter(value = AccessLevel.NONE)
    private Long id;

    private String firstName;
    private String lastName;
    private String criticisms;
    private String munVistdPercnt;

    @OneToMany(mappedBy = "candidate")
    private List<CandidateAdvocacy> candidateAdvocacies;

    public Candidate() {}
    public Candidate(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
