package com.enablero.todo.dataprovider.impl;

import com.enablero.todo.dataprovider.TodoDataProvider;
import com.enablero.todo.entity.TodoEntity;
import com.enablero.todo.model.Todo;
import com.enablero.todo.model.TodoStatus;
import com.enablero.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoDataProviderImpl implements TodoDataProvider {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoDataProviderImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public List<Todo> getAllTodos(String email) {
        return todoRepository.findByEmail(email).stream()
                .filter(todoEntity -> !TodoStatus.ARCHIVED.equals(todoEntity.getStatus()))
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Todo createOrUpdateTodo(Todo todo) {
        TodoEntity todoEntity = convertToEntity(todo);
        TodoEntity savedEntity = todoRepository.save(todoEntity);
        return convertToModel(savedEntity);
    }

    @Override
    public Todo findById(String id) {
        return convertToModel(todoRepository.findById(id).orElse(null));
    }

    private Todo convertToModel(TodoEntity entity) {
        if (entity == null) {
            return null;
        }
        Todo todo = new Todo();
        todo.setId(entity.getId());
        todo.setEmail(entity.getEmail());
        todo.setTitle(entity.getTitle());
        todo.setDescription(entity.getDescription());
        todo.setStatus(entity.getStatus());
        todo.setCreatedDt(entity.getCreatedDt());
        todo.setUpdateDt(entity.getUpdateDt());
        return todo;
    }

    private TodoEntity convertToEntity(Todo todo) {
        if (todo == null) {
            return null;
        }
        TodoEntity entity = new TodoEntity();
        entity.setId(todo.getId());
        entity.setEmail(todo.getEmail());
        entity.setTitle(todo.getTitle());
        entity.setDescription(todo.getDescription());
        entity.setStatus(todo.getStatus());
        entity.setCreatedDt(todo.getCreatedDt());
        entity.setUpdateDt(todo.getUpdateDt());
        return entity;
    }

}
