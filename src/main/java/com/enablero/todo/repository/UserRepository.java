package com.enablero.todo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.enablero.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User saveUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User getUserById(String id) {
        return dynamoDBMapper.load(User.class, id);
    }
}
