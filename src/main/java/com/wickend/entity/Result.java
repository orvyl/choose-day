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
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long candidateId;
    private String items;

    public Result() {}
    public Result(Long candidateId, String items) {
        this.candidateId = candidateId;
        this.items = items;
    }
}
