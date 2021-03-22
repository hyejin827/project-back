package com.project.projectback.repository;

import com.project.projectback.model.Comment;
import com.project.projectback.model.ToDo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface ToDoRepository extends ReactiveCrudRepository<ToDo, String> {

//    테스트
//    @Query("select id, contents from to_do_list t where t.num = :num")
//    Flux<ToDo> findToDoByNum(Integer num);

//    @Query("update to_do_list todo set todo.todo_mod_dtime = ?2 where todo.todo_id = ?1")
//    void updateToDoById(String todo_id, LocalDateTime todo_mod_dtime);

//    @Query("INSERT INTO to_do_list (todo_id, todo_user,todo_contents,todo_reg_id,todo_reg_dtime,todo_mod_id,todo_mod_dtime) values (nextval('todo_id_seq')||'_' ||to_char(now(), 'YYMMDD') , :,'webfilter 적용하기','phyej827',now(),'phyej827',now());")
//    void saveToDo(ToDo todo);

    @Query("select * from to_do_list c where c.todo_id = :todo_id")
    Mono<ToDo> findByTodoSeq(String todo_id);

    @Query("select nextval('todo_id_seq')")
    Mono<Integer> getNextSeriesId();
}
