package com.project.projectback.repository;

import com.project.projectback.model.Comment;
import com.project.projectback.model.ToDo;
import reactor.core.publisher.Flux;

public interface CustomCommentRepository {
    Flux<Comment> findAllComments (String toDoId);
}
