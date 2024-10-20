package com.enablero.todo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.enablero.todo.entity.TodoEntity;
import com.enablero.todo.model.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
        String filterForDelete = "#status <> :status";

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":status", new AttributeValue("ARCHIVED"));
        expressionAttributeValues.put(":email", new AttributeValue().withS(email));

        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#status", "status");

        return dynamoDBMapper.query(TodoEntity.class, new DynamoDBQueryExpression<TodoEntity>()
                .withIndexName("email")
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(expressionAttributeValues)
                .withFilterExpression(filterForDelete)
                .withExpressionAttributeNames(expressionAttributeNames)
                .withConsistentRead(false));
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

