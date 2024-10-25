package com.enablero.todo.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private String id;
    private String email;
    private String title;
    private String description;
    private TodoStatus status;
    private LocalDateTime createdDt;
    private LocalDateTime updateDt;
}
