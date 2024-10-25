package com.enablero.todo.dataprovider;

import com.enablero.todo.model.Todo;

import java.util.List;

public interface TodoDataProvider {
    List<Todo> getAllTodos(String email);
    Todo createOrUpdateTodo(Todo todo);
    Todo findById(String id);

}
