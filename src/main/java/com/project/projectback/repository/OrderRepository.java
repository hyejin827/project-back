package com.project.projectback.repository;

import com.project.projectback.model.OmOdDtl;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

public interface OrderRepository {

    Flux<OmOdDtl> findById(String odNo, String clmNo);
}
