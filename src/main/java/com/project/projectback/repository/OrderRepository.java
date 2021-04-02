package com.project.projectback.repository;

import com.project.projectback.model.OmOd;
import com.project.projectback.model.OmOdDtl;
import com.project.projectback.model.OmOdDtl2;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {

    Flux<OmOd> findAll();
    Flux<OmOdDtl> findById(String odNo, String clmNo);
    Flux<OmOdDtl> findById(String odNo);
    Mono<Integer> updateById(String odNo);
    Mono<OmOdDtl> insertById(String odNo);
}
