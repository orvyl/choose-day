package com.wickend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by vyl on 4/16/16.
 */
@Data
@Entity
public class CandidateAdvocacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Candidate candidate;
    private Long advocacyId;

    public CandidateAdvocacy() {}
    public CandidateAdvocacy(Candidate candidate, Long advocacyId) {
        this.candidate = candidate;
        this.advocacyId = advocacyId;
    }
}
