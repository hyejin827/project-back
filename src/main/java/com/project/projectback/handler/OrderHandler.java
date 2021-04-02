package com.project.projectback.handler;

import com.project.projectback.model.*;
import com.project.projectback.repository.CommentRepository;
import com.project.projectback.repository.OrderRepository;
import com.project.projectback.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;

    public Mono<ServerResponse> findOmOdList(ServerRequest serverRequest) {
        Flux<OmOd> omOdList = orderRepository.findAll();
//        toDoList.subscribe(System.out::println);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderRepository.findAll(), ToDo.class);
    }

    public Mono<ServerResponse> findOmOdDtlList(ServerRequest serverRequest) {
//        serverRequest.bodyToMono(JSONObject.class);
        String odNo = serverRequest.pathVariable("odNo");
        String clmNo = serverRequest.pathVariable("clmNo");
        Flux<OmOdDtl> omOdDtlFlux = orderRepository.findById(odNo,clmNo).cache();
        omOdDtlFlux.subscribe(value -> System.out.println("omOdDtlFlux=======>" + value));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderRepository.findById(odNo,clmNo), OmOdDtl.class);
    }

    public Mono<ServerResponse> findOmOdDtlList2(ServerRequest serverRequest) {
//        serverRequest.bodyToMono(JSONObject.class);
        String odNo = serverRequest.pathVariable("odNo");
        Flux<OmOdDtl> omOdDtlFlux = orderRepository.findById(odNo).cache();
        omOdDtlFlux.subscribe(value -> System.out.println("omOdDtl2Flux=======>" + value));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderRepository.findById(odNo), OmOdDtl.class);
    }

    public Mono<ServerResponse> saveOmOdDtl(ServerRequest serverRequest) {
        String odNo = serverRequest.pathVariable("odNo");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderRepository.updateById(odNo), OmOdDtl.class);
    }
}
