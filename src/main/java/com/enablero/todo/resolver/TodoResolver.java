package com.enablero.todo.resolver;

import com.enablero.todo.entity.Todo;
import com.enablero.todo.service.TodoService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoResolver implements GraphQLQueryResolver , GraphQLMutationResolver {

    @Autowired
    private TodoService todoService;

    public List<Todo> getAllTodos(String userId){
        return todoService.getAllTodos(userId);
    }

    public Todo getTodoById(String id) {
        return todoService.getTodoById(id);
    }
    public Todo createTodo(String userId, String title, String description, String status) {
        return todoService.createTodo(userId, title, description, status);
    }


    public Todo deleteTodo(String id) {
        return todoService.deleteTodo(id);
    }


}
