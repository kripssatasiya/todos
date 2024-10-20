package com.enablero.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {
    private String id;
    private String email;
    private String title;
    private String description;
    private TodoStatus status;
    private LocalDateTime createdDt;
    private LocalDateTime updateDt;

}
