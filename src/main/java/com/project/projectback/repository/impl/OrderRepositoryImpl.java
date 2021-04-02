package com.project.projectback.repository.impl;

import com.project.projectback.model.OmOd;
import com.project.projectback.model.OmOdDtl;
import com.project.projectback.model.OmOdDtl2;
import com.project.projectback.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Repository
@Transactional
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Flux<OmOd> findAll() {
        return template.select(OmOd.class)
                .from("om_od")
                .all();
    }

    @Override
    public Flux<OmOdDtl> findById(String odNo, String clmNo) {
        return template.select(OmOdDtl.class)
                .from("om_od_dtl")
                .matching(query(where("od_no").is(odNo)
                .and("clm_no").is(clmNo))).all();
    }

    @Override
    public Flux<OmOdDtl> findById(String odNo) {
        return template.select(OmOdDtl.class)
                .from("om_od_dtl")
                .matching(query(where("od_no").is(odNo)))
                .all();
    }

    @Override
    public Mono<Integer> updateById(String odNo) {
        return template.update(OmOdDtl.class)
                .inTable("om_od_dtl")
                .matching(query(where("od_no").is(odNo)))
                .apply(Update.update("od_qty",0)
                            .set("cncl_qty",1)
                            .set("mod_dttm", LocalDateTime.now()));

    }

    @Override
    public Mono<OmOdDtl> insertById(String odNo) {
        Flux<OmOdDtl> omodDtl = findById(odNo);
        OmOdDtl omOdDtl = new OmOdDtl();

        return template.insert(omOdDtl);
    }
}
