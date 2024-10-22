package com.enablero.todo.dataprovider;

import com.enablero.todo.entity.UserEntity;

import java.util.List;

public interface UserDataProvider {

    UserEntity findByEmail(String emailId);
    List<String> findAll();
}
