package com.project.projectback.router;

import com.project.projectback.handler.ToDoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ToDoRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ToDoHandler toDoHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), toDoHandler::flux)
                .andRoute(
                        RequestPredicates.GET("/todo/findAll").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , toDoHandler::findAllToDoList
                )
                .andRoute(
                        RequestPredicates.POST("/todo/save").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , toDoHandler::saveToDo
                )
                .andRoute(
                        RequestPredicates.GET("/todo/find/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , toDoHandler::findToDoById
                )
                .andRoute(
                        RequestPredicates.POST("/todo/update/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , toDoHandler::updateToDoById
                )
                .andRoute(
                        RequestPredicates.GET("/todo/delete/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , toDoHandler::deleteToDoById
                )
                .andRoute(
                        RequestPredicates.GET("/todo/comment").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , toDoHandler::findComments
                )
                .andRoute(
                        RequestPredicates.GET("/todo/comment/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
                        , toDoHandler::findCommentsSingle
                );
    }
}
