package com.enablero.todo.service.impl;

import com.enablero.todo.repository.UserRepository;
import com.enablero.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllowListByUsers() {
        return userRepository.findAll();
    }

}
