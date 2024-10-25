package com.enablero.todo.dataprovider.impl;

import com.enablero.todo.dataprovider.UserDataProvider;
import com.enablero.todo.entity.UserEntity;
import com.enablero.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataProviderImpl implements UserDataProvider {

    private final UserRepository userRepository;
    @Autowired
    public UserDataProviderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByEmail(String emailId) {
        List<UserEntity> allUsers = (List<UserEntity>) userRepository.findAll();

        if (CollectionUtils.isEmpty(allUsers)) {
            return null;
        }

        return allUsers.stream()
                .filter(user -> emailId.equals(user.getEmailId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<String> findAll() {
        List<UserEntity> allUsers = (List<UserEntity>) userRepository.findAll();

        if (CollectionUtils.isEmpty(allUsers)) {
            return List.of();
        }

        return allUsers.stream()
                .map(UserEntity::getEmailId)
                .filter(email -> email != null)
                .collect(Collectors.toList());
    }
}
