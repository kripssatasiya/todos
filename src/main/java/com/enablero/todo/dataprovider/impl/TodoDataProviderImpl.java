package com.enablero.todo.dataprovider.impl;

import com.enablero.todo.dataprovider.TodoDataProvider;
import com.enablero.todo.entity.TodoEntity;
import com.enablero.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoDataProviderImpl implements TodoDataProvider {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoDataProviderImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoEntity> getAllTodos(String email) {
        return todoRepository.getAllTodos(email);
    }

    @Override
    public TodoEntity createOrUpdateTodo(TodoEntity todo) {
        return todoRepository.createOrUpdateTodo(todo);
    }

    @Override
    public TodoEntity findById(String id) {
        return todoRepository.findById(id);
    }
}
