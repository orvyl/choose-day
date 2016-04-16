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

    @ManyToOne
    @JoinColumn
    private Advocacy advocacy;

    public CandidateAdvocacy() {}
    public CandidateAdvocacy(Candidate candidate, Advocacy advocacy) {
        this.candidate = candidate;
        this.advocacy = advocacy;
    }
}
