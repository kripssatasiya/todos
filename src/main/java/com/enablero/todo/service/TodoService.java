package com.enablero.todo.service;

import com.enablero.todo.entity.Todo;
import com.enablero.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(String userId){
        return todoRepository.getAllTodos(userId);
    }

    public Todo getTodoById(String id) {
        return todoRepository.getTodoById(id);
    }

    public Todo createTodo(String userId, String title, String description, String status){
        Todo todo = new Todo();
        todo.setUserId(userId);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setStatus(status);
        return todoRepository.createTodo(todo);
    }

    public Todo deleteTodo(String id) {
        Todo todo = todoRepository.getTodoById(id);
        if (todo != null) {
            todo.setStatus("DELETED");
            todoRepository.createTodo(todo);
        }
        return todo;
    }
}
