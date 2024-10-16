package com.enablero.todo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.enablero.todo.entity.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepository {

    private DynamoDBMapper dynamoDBMapper;
    @Autowired
    public TodoRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public List<TodoEntity> getAllTodos(String email) {
        return dynamoDBMapper.query(TodoEntity.class, new DynamoDBQueryExpression<TodoEntity>()
                .withIndexName("UserGSI")
                .withConsistentRead(false)
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(Map.of(":email", new AttributeValue().withS(email))));
    }

    public TodoEntity findByIdEmail(String id , String email) {
        return dynamoDBMapper.load(TodoEntity.class, id,email);
    }


    public TodoEntity findById(String id) {
        return dynamoDBMapper.load(TodoEntity.class, id);
    }


    public TodoEntity createOrUpdateTodo(TodoEntity todo) {
        dynamoDBMapper.save(todo);
        return todo;
    }


}

