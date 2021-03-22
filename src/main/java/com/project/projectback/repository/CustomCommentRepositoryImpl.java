package com.project.projectback.repository;

import com.project.projectback.mapper.CommentMapper;
import com.project.projectback.model.Comment;
import com.project.projectback.model.ToDo;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.With;
import org.springframework.data.annotation.Transient;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CustomCommentRepositoryImpl implements CustomCommentRepository{
    private DatabaseClient client;
    private ToDoRepository toDoRepository;

    public CustomCommentRepositoryImpl(DatabaseClient client) {
        this.client = client;
    }

    @Override
    public Flux<Comment> findAllComments(String toDoId) {
        String query = "SELECT comment_id, comment_cont FROM comment_list a , to_do_list b where comment_id=todo_id and comment_id = :todo_id";
        CommentMapper mapper = new CommentMapper();
        Flux<Comment> result = client.sql(query)
                .bind("todo_id", toDoId)
                .map(mapper::apply)
                .all();

        return result;
    }


}
