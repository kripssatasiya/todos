package com.enablero.todo.dataprovider.impl;

import com.enablero.todo.dataprovider.UserDataProvider;
import com.enablero.todo.entity.UserEntity;
import com.enablero.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataProviderImpl implements UserDataProvider {

    private final UserRepository userRepository;
    @Autowired
    public UserDataProviderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByEmail(String emailId) {
        return userRepository.findByEmail(emailId);
    }

    @Override
    public List<String> findAll() {
        return userRepository.findAll();
    }
}
