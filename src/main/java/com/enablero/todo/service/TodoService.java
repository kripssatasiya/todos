package com.enablero.todo.service;

import com.enablero.todo.entity.Todo;
import com.enablero.todo.model.TodoInput;
import com.enablero.todo.model.TodoStatus;
import com.enablero.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(String email){
        return todoRepository.getAllTodos(email);
    }

    public Todo createOrUpdateTodo(TodoInput input){
        Todo existingTodo = todoRepository.findById(input.getId());
        if (existingTodo != null) {
            existingTodo.setTitle(input.getTitle());
            existingTodo.setDescription(input.getDescription());
            existingTodo.setStatus(input.getStatus());
            existingTodo.setUpdateDt(input.getUpdateDt());
       }
        else{
            Todo newTodo = new Todo();
            newTodo.setId(input.getId());
            newTodo.setEmail(input.getEmail());
            newTodo.setTitle(input.getTitle());
            newTodo.setDescription(input.getDescription());
            newTodo.setStatus(input.getStatus());
            newTodo.setCreatedDt(LocalDateTime.now());
            newTodo.setUpdateDt(LocalDateTime.now());
            existingTodo = newTodo;
        }
        return todoRepository.createOrUpdateTodo(existingTodo);
    }

    public String deleteTodo(String id) {
        Todo todo = todoRepository.findById(id);
        if (todo != null) {
            todo.setStatus(TodoStatus.ARCHIVED);
            return "Todo marked as deleted!";
        }
         return "Todo not found.";

    }
}
