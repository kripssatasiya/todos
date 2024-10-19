package com.enablero.todo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.enablero.todo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public UserEntity findByEmail(String emailId) {
        Map<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put(":email", new AttributeValue().withS(emailId));

        DynamoDBQueryExpression<UserEntity> queryExpression = new DynamoDBQueryExpression<UserEntity>()
                .withIndexName("UserEmail")
                .withKeyConditionExpression("emailId = :email")
                .withExpressionAttributeValues(attributeValues)
                .withConsistentRead(false);

        List<UserEntity> result = dynamoDBMapper.query(UserEntity.class, queryExpression);
        return result.isEmpty() ? null : result.get(0);
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
