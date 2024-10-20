package com.enablero.todo.resolver;

import com.enablero.todo.entity.TodoEntity;
import com.enablero.todo.model.Todo;
import com.enablero.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoResolver {

    private TodoService todoService;

    @Autowired
    public TodoResolver(TodoService todoService) {
        this.todoService = todoService;
    }


    @QueryMapping("getAllTodos")
    public List<TodoEntity> getAllTodos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaimAsString("email");
        System.out.println("Email extracted and passed to service = " +email);
        return todoService.getAllTodos(email);
    }


    @MutationMapping("createOrUpdateTodo")
    public TodoEntity createOrUpdateTodo(@Argument("input") Todo input) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaimAsString("email");
        return todoService.createOrUpdateTodo(input , email);
    }

   @MutationMapping("deleteTodo")
    public String deleteTodo(@Argument("id") String id) {
        return todoService.deleteTodo(id);
    }


}
