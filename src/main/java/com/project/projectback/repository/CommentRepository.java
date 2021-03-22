package com.project.projectback.repository;

import com.project.projectback.model.Comment;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveCrudRepository<Comment, String> {

    @Query("select * from comment_list c where c.todo_id = :todo_id")
    Flux<Comment> findByTodoSeq(String todo_id);
}
