package com.wickend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by vyl on 4/16/16.
 */
@Data
@Entity
public class CandidateAdvocacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long advocacyId;
    private Long candidateId;
}
