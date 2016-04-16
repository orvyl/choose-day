package com.wickend.repo;

import com.wickend.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vyl on 4/16/16.
 */
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
}
