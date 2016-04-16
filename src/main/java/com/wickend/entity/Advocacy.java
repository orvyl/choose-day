package com.wickend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vyl on 4/16/16.
 */
@Data
@Entity
public class Advocacy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "advocacy")
    private List<CandidateAdvocacy> candidateAdvocacies;

    public Advocacy() {}
    public Advocacy(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
