package com.enablero.todo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.enablero.todo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

//    public UserEntity save(UserEntity userEntity) {
//        dynamoDBMapper.save(userEntity);
//        return userEntity;
//    }

    public UserEntity findByEmail(String emailId) {
        return dynamoDBMapper.load(UserEntity.class, emailId);
    }


    public List<String> findAll(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<UserEntity> users = dynamoDBMapper.scan(UserEntity.class, scanExpression);
        if (users == null || users.isEmpty()) {
            return List.of();
        }

        List<String> emails = users.stream()
                .map(UserEntity::getEmailId)
                .filter(email -> email != null)
                .collect(Collectors.toList());
        return emails;
    }


}
