package com.enablero.todo.resolver;

import com.enablero.todo.entity.UserEntity;
import com.enablero.todo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResolver {

    @Autowired
    private UserService userService;

//    @MutationMapping
//    public UserEntity findOrCreateUser(String emailId){
//        return userService.findOrCreateUser(emailId);
//    }

    @QueryMapping("getAllowListByUsers")
    public List<String> getAllowListByUsers() {
        return userService.getAllowListByUsers();
    }

}
