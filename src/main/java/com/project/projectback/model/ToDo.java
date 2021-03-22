package com.project.projectback.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table("to_do_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class
ToDo {

    @Id
    private String todo_id;

    @Column
    private String todo_user;

    @Column
    private String todo_contents;

    @Column
    private String todo_reg_id;

    @Column
    private LocalDateTime todo_reg_dtime;

    @Column
    private String todo_mod_id;

    @Column
    private LocalDateTime todo_mod_dtime;

//    @PersistenceConstructor
//    public ToDo(String todo_id, String todo_user, String todo_contents, String todo_reg_id, LocalDateTime todo_reg_dtime, String todo_mod_id, LocalDateTime todo_mod_dtime, List<Comment> commentList) {
//        this.todo_id = todo_id;
//        this.todo_user = todo_user;
//        this.todo_contents = todo_contents;
//        this.todo_reg_id = todo_reg_id;
//        this.todo_reg_dtime = todo_reg_dtime;
//        this.todo_mod_id = todo_mod_id;
//        this.todo_mod_dtime = todo_mod_dtime;
//        this.commentList = commentList;
//    }

    @With
    @Transient
    private List<Comment> commentList;
}
