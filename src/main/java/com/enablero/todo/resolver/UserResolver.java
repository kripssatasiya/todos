package com.enablero.todo.resolver;

import com.enablero.todo.entity.User;
import com.enablero.todo.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLQueryResolver , GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    public User saveUser(String emailId){
       return userService.saveUser(emailId);
    }

    public User getUserById(String id) {
        return userService.getUserById(id);
    }

}
