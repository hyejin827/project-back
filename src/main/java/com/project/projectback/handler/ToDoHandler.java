package com.project.projectback.handler;

import com.project.projectback.model.Comment;
import com.project.projectback.model.ToDo;
import com.project.projectback.repository.CommentRepository;
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
public class ToDoHandler {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Mono<ServerResponse> flux(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Flux.just(1, 2, 3, 4)
                                .log(), Integer.class
                );
    }

    public Mono<ServerResponse> findAllToDoList(ServerRequest serverRequest) {
        Flux<ToDo> toDoList = toDoRepository.findAll();
//        toDoList.subscribe(System.out::println);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toDoRepository.findAll(), ToDo.class);
    }

    public Mono<ServerResponse> saveToDo(ServerRequest serverRequest){
        Mono<ToDo> toDoMono = serverRequest.bodyToMono(ToDo.class);
        log.info("========================save========================");
        return toDoMono.flatMap(todo ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(toDoRepository.save(todo),ToDo.class));
    }

    public Mono<ServerResponse> findToDoById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<ToDo> toDoByNum = toDoRepository.findById(id);
        toDoByNum.subscribe(System.out::println);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(toDoRepository.findById(id), ToDo.class);
//                .switchIfEmpty(ServerResponse.notFound().build());

//        return toDoByNum.flatMap(todo ->
//            ServerResponse.ok()
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(BodyInserters.fromValue(toDoByNum))
//            ).switchIfEmpty(ServerResponse.notFound().build());

    }

//    @CrossOrigin
//    public Mono<ServerResponse> updateToDoById(ServerRequest serverRequest){
//        String todoId = serverRequest.pathVariable("id");
//        log.info("update id => {}", todoId);
//        Mono<ToDo> toDoMono = serverRequest.bodyToMono(ToDo.class);
//
//        log.info("========================update========================");
//         toDoMono.flatMap(todo -> {
//             todo.setTodo_mod_dtime(LocalDateTime.now());
//             return Mono.just(todo); });
//
//         return toDoMono.flatMap(todo ->
//                 ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(toDoRepository.save(todo),ToDo.class));
//    }

    @CrossOrigin
    public Mono<ServerResponse> updateToDoById(ServerRequest serverRequest) {
        String todoId = serverRequest.pathVariable("id");
        log.info("update id => {}", todoId);
        Mono<ToDo> toDoMono = serverRequest.bodyToMono(ToDo.class);

        log.info("========================update========================");
//        toDoMono.flatMap(todo -> {
//            todo.setTodo_mod_dtime(LocalDateTime.now());
//            return Mono.just(todo); });
//
//        return toDoMono.flatMap(todo ->
//                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromProducer(toDoRepository.save(todo),ToDo.class)));

        return serverRequest.bodyToMono(ToDo.class).map(todo -> {
            todo.setTodo_mod_dtime(LocalDateTime.now());
            return todo;
        }).flatMap(todo ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromProducer(toDoRepository.save(todo), ToDo.class)));
    }

    @CrossOrigin
    public Mono<ServerResponse> deleteToDoById(ServerRequest serverRequest) {
        String todoId = serverRequest.pathVariable("id");
        log.info("delete id => {}", todoId);
        Mono<ToDo> toDoById = toDoRepository.findById(todoId);

        return toDoById.flatMap(todo ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(toDoRepository.delete(todo), Void.class)
        ).switchIfEmpty(ServerResponse.notFound().build());
    }

//    public Flux<ToDo> findComments() {
//        return toDoRepository.findAll().cache().flatMap(todo ->
//                Mono.just(todo)
//                        .zipWith(commentRepository.findByTodoSeq(todo.getTodo_id()).cache().collectList())
//                        .map(assemble -> assemble.getT1().withCommentList(assemble.getT2()))
//        );
//    }

    public Mono<ServerResponse> findComments(ServerRequest request) {
        Flux<ToDo> toDoFlux = toDoRepository.findAll().cache();
        Flux<List<Comment>> commentListFlux = toDoFlux.map(ToDo::getTodo_id)
                .flatMap(todo_id -> commentRepository.findByTodoSeq(todo_id).collectList());
        return Flux.zip(toDoFlux, commentListFlux, ToDo::withCommentList).collectList().flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> findCommentsSingle(ServerRequest serverRequest) {
        String todoId = serverRequest.pathVariable("id");
        Mono<ToDo> toDoMono = toDoRepository.findByTodoSeq(todoId).cache();
        Flux<Comment> commentListFlux = commentRepository.findByTodoSeq(todoId).cache();

        return toDoMono.zipWith(commentListFlux.collectList())
				.map(assemble -> assemble.getT1().withCommentList(assemble.getT2())).flatMap(ServerResponse.ok()::bodyValue);
    }

}
