package com.project.projectback.config;

import com.project.projectback.model.ToDo;
import com.project.projectback.repository.ToDoRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Test implements BeforeConvertCallback<ToDo>, BeforeSaveCallback<ToDo> {
    @Autowired
    private ToDoRepository todoRepository;


    @Override
    public Publisher<ToDo> onBeforeConvert(ToDo entity, SqlIdentifier table) {
        // TODO Auto-generated method stub
        System.out.println("1.BeforeConvertCallback_TodoCallBacks_onBeforeConvert");


        if (entity.getTodo_id() == null || entity.getTodo_id() == "") {
            return todoRepository.getNextSeriesId().map(seq -> {
                entity.setTodo_reg_dtime(LocalDateTime.now());
                entity.setTodo_id("todo" + seq);
                return entity;
            });
        } else {
            entity.setTodo_mod_dtime(LocalDateTime.now());
            Mono<ToDo> todo = Mono.just(entity);
            return todo;
        }
    }


    @Override
    public Publisher<ToDo> onBeforeSave(ToDo entity, OutboundRow row, SqlIdentifier table) {
        // TODO Auto-generated method stub
        System.out.println("6.BeforeConvertCallback_TodoCallBacks_onBeforeSave");
        Mono<ToDo> todo = Mono.just(entity);
        return todo;
    }
}
