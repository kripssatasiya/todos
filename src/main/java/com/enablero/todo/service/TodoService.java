package com.enablero.todo.service;

import com.enablero.todo.entity.TodoEntity;
import com.enablero.todo.model.Todo;
import com.enablero.todo.model.TodoStatus;
import com.enablero.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoEntity> getAllTodos(String email) {
        System.out.println("Email  passed to repository = " +email);
        return todoRepository.getAllTodos(email);
    }

    public TodoEntity createOrUpdateTodo(Todo todoInput , String email) {
        if (todoInput == null) {
            throw new RuntimeException("TodoInput object cannot be null");
        }

        TodoEntity todo;
        if (todoInput.getId() != null) {
            todo = todoRepository.findById(todoInput.getId());
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
        System.out.println(todo.toString());
        return todoRepository.createOrUpdateTodo(todo);
    }

    public String deleteTodo(String id) {
        TodoEntity todoEntity = todoRepository.findById(id);
        if (todoEntity != null) {
            todoEntity.setStatus(TodoStatus.ARCHIVED);
            todoRepository.createOrUpdateTodo(todoEntity);
            return "Todo marked as deleted!";
        }
         return "Todo not found.";

    }
}
