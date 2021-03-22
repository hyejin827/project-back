package com.project.projectback.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("comment_list")
@Data
public class Comment {

    @Id
    private String comment_id;

    @Column
    private String comment_cont;

    @Column
    private String todo_id;

    @Column
    private String comment_reg_id;

    @Column
    private LocalDateTime comment_reg_dtime;

    @Column
    private String comment_mod_id;

    @Column
    private LocalDateTime comment_mod_dtime;

    public Comment(String comment_id, String comment_cont, String todo_id, String comment_reg_id, LocalDateTime comment_reg_dtime, String comment_mod_id, LocalDateTime comment_mod_dtime) {
        this.comment_id = comment_id;
        this.comment_cont = comment_cont;
        this.todo_id = todo_id;
        this.comment_reg_id = comment_reg_id;
        this.comment_reg_dtime = comment_reg_dtime;
        this.comment_mod_id = comment_mod_id;
        this.comment_mod_dtime = comment_mod_dtime;
    }
}
