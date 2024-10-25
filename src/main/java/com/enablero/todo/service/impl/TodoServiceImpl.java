package com.enablero.todo.service.impl;

import com.enablero.todo.dataprovider.TodoDataProvider;
import com.enablero.todo.model.Todo;
import com.enablero.todo.model.TodoStatus;
import com.enablero.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoDataProvider todoDataProvider;
    @Autowired
    public TodoServiceImpl(TodoDataProvider todoDataProvider) {
        this.todoDataProvider = todoDataProvider;
    }


    @Override
    public List<Todo> getAllTodos(String email) {
        return todoDataProvider.getAllTodos(email);
    }

    @Override
    public Todo createOrUpdateTodo(Todo todoInput, String email) {
        if (todoInput == null) {
            throw new RuntimeException("TodoInput object cannot be null");
        }

        Todo todo;
        if (todoInput.getId() != null) {
            todo = todoDataProvider.findById(todoInput.getId());
            if (todo == null) {
                throw new RuntimeException("Todo not found");
            }
            if (!todo.getEmail().equals(email)) {
                throw new RuntimeException("Unauthorized access to the Todo");
            }
        } else {
            todo = new Todo();
            todo.setId(UUID.randomUUID().toString());
            todo.setCreatedDt(LocalDateTime.now(ZoneOffset.UTC));
            todo.setStatus(TodoStatus.PENDING);
        }

        todo.setEmail(email);
        todo.setTitle(todoInput.getTitle());
        todo.setDescription(todoInput.getDescription());
        if (todoInput.getStatus() != null) {
            todo.setStatus(todoInput.getStatus());
        }
        todo.setUpdateDt(LocalDateTime.now(ZoneOffset.UTC));
        return todoDataProvider.createOrUpdateTodo(todo);
    }

    @Override
    public String deleteTodo(String id) {
        Todo todo = todoDataProvider.findById(id);
        if (todo == null) {
            throw new RuntimeException("Todo not found");
        }
        todo.setStatus(TodoStatus.ARCHIVED);
        todo.setUpdateDt(LocalDateTime.now(ZoneOffset.UTC));
        Todo updatedTodo = todoDataProvider.createOrUpdateTodo(todo);
        if (updatedTodo == null) {
            throw new RuntimeException("Failed to update Todo status");
        }
        return "Todo successfully deleted!!";
    }
}
