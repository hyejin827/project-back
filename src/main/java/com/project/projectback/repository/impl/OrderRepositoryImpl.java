package com.project.projectback.repository.impl;

import com.project.projectback.model.OmOdDtl;
import com.project.projectback.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Repository
@Transactional
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Flux<OmOdDtl> findById(String odNo, String clmNo) {
        return template.select(OmOdDtl.class)
                .from("om_od_dtl")
                .matching(query(where("od_no").is(odNo)
                .and("clm_no").is(clmNo))).all();
    }
}
