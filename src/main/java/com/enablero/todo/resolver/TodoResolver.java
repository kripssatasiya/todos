package com.enablero.todo.resolver;

import com.enablero.todo.entity.Todo;
import com.enablero.todo.model.TodoInput;
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

    public List<Todo> getAllTodos(String email){
        return todoService.getAllTodos(email);
    }


    public Todo createOrUpdateTodo(TodoInput input) {
        return todoService.createOrUpdateTodo(input);
    }


    public String deleteTodo(String id) {
        return todoService.deleteTodo(id);
    }


}
