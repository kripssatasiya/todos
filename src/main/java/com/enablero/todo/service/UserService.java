package com.enablero.todo.service;

import com.enablero.todo.entity.User;
import com.enablero.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOrCreateUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmailId(email);
            userRepository.save(user);
        }
        return user;
    }

    public User getAllowListByUsers(String emailId) {
        return userRepository.findByEmail(emailId);
    }

}
