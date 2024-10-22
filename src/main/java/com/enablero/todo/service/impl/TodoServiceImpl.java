package com.enablero.todo.service.impl;

import com.enablero.todo.dataprovider.TodoDataProvider;
import com.enablero.todo.entity.TodoEntity;
import com.enablero.todo.model.Todo;
import com.enablero.todo.model.TodoStatus;
import com.enablero.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoDataProvider todoDataProvider;
    @Autowired
    public TodoServiceImpl(TodoDataProvider todoDataProvider) {
        this.todoDataProvider = todoDataProvider;
    }

    @Override
    public List<TodoEntity> getAllTodos(String email) {
        System.out.println("Email  passed to repository = " +email);
        return todoDataProvider.getAllTodos(email);
    }

    @Override
    public TodoEntity createOrUpdateTodo(Todo todoInput , String email) {
        if (todoInput == null) {
            throw new RuntimeException("TodoInput object cannot be null");
        }

        TodoEntity todo;
        if (todoInput.getId() != null) {
            todo = todoDataProvider.findById(todoInput.getId());
            if (todo == null || !todo.getEmail().equals(email)) {
                throw new RuntimeException("Todo not found or unauthorized access");
            }
        } else {
            todo = new TodoEntity();
            todo.setCreatedDt(LocalDateTime.now());
            todo.setStatus(TodoStatus.PENDING);
        }

        todo.setEmail(email);

        if (todoInput.getTitle() != null) {
            todo.setTitle(todoInput.getTitle());
        }
        if (todoInput.getDescription() != null) {
            todo.setDescription(todoInput.getDescription());
        }
        if (todoInput.getStatus() != null) {
            todo.setStatus(todoInput.getStatus());
        }
        todo.setUpdateDt(LocalDateTime.now());
        return todoDataProvider.createOrUpdateTodo(todo);
    }

    @Override
    public String deleteTodo(String id) {
        TodoEntity todoEntity = todoDataProvider.findById(id);
        if (todoEntity != null) {
            todoEntity.setStatus(TodoStatus.ARCHIVED);
            todoDataProvider.createOrUpdateTodo(todoEntity);
            return "Todo marked as deleted!";
        }
        return "Todo not found.";
    }

}
