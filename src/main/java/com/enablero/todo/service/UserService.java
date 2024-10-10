package com.enablero.todo.service;

import com.enablero.todo.entity.User;
import com.enablero.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(String emailId) {
        User user =  new User();
        user.setEmailId(emailId);
        return userRepository.saveUser(user);
    }

    public User getUserById(String id) {
        return userRepository.getUserById(id);
    }

}
