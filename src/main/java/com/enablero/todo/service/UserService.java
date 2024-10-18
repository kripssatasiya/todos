package com.enablero.todo.service;

import com.enablero.todo.entity.UserEntity;
import com.enablero.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<String> getAllowListByUsers() {
        return userRepository.findAll();
    }

}
