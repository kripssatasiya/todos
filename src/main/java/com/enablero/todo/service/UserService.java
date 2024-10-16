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

//    public UserEntity findOrCreateUser(String email) {
//        UserEntity userEntity = userRepository.findByEmail(email);
//        if (userEntity == null) {
//            userEntity = new UserEntity();
//            userEntity.setEmailId(email);
//            userRepository.save(userEntity);
//        }
//        return userEntity;
//    }

    public List<String> getAllowListByUsers() {
        return userRepository.findAll();
    }

}
