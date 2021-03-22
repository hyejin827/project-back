package com.project.projectback.mapper;

import com.project.projectback.model.Comment;
import com.project.projectback.model.ToDo;
import io.r2dbc.spi.Row;

import java.util.function.BiFunction;

public class CommentMapper implements BiFunction<Row, Object, Comment> {
    @Override
    public Comment apply(Row row, Object o) {
        String toDoId = row.get("todo_id", String.class);
        String commentId = row.get("comment_id", String.class);
        String commentCont = row.get("comment_cont", String.class);

//        ToDo toDo = new ToDo(toDoId);
//        Comment comment = new Comment(toDoId, commentId);
//
//        return comment;

//        UUID taskId = row.get("task_id", UUID.class);
//        String content = row.get("task_content", String.class);
//        Boolean completed = row.get("is_completed", Boolean.class);
//        LocalDate createdAt = row.get("task_date", LocalDate.class);
//        UUID projectId = row.get("project_id", UUID.class);
//        String projectName = row.get("project_name", String.class);
//        Project project = new Project(projectId, projectName);
//        Task task = new Task(taskId, content, complted, createdAt, project);
//        return task;

        return null;
    }
}
