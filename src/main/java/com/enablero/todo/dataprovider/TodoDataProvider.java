package com.enablero.todo.dataprovider;

import com.enablero.todo.entity.TodoEntity;

import java.util.List;

public interface TodoDataProvider {
    List<TodoEntity> getAllTodos(String email);
    TodoEntity createOrUpdateTodo(TodoEntity todo);
    TodoEntity findById(String id);
}
