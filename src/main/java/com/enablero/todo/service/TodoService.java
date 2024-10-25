package com.enablero.todo.service;

import com.enablero.todo.model.Todo;
import java.util.List;

public interface TodoService {

    List<Todo> getAllTodos(String email);
    Todo createOrUpdateTodo(Todo todoInput, String email);
    String deleteTodo(String id);
}
