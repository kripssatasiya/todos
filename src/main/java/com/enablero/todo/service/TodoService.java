package com.enablero.todo.service;

import com.enablero.todo.entity.TodoEntity;
import com.enablero.todo.model.Todo;
import java.util.List;

public interface TodoService {

    List<TodoEntity> getAllTodos(String email);

    TodoEntity createOrUpdateTodo(Todo todoInput , String email);

    String deleteTodo(String id);

}
