package com.enablero.todo.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.enablero.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TodoRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public List<Todo> getAllTodos(String userId) {
        return dynamoDBMapper.query(Todo.class, new DynamoDBQueryExpression<Todo>()
                .withIndexName("UserGSI")
                .withKeyConditionExpression("userId = :userId")
                .withExpressionAttributeValues(Map.of(":userId", new AttributeValue().withS(userId))));
    }

    public Todo getTodoById(String id) {
        return dynamoDBMapper.load(Todo.class, id);
    }

    public Todo createTodo(Todo todo) {
        dynamoDBMapper.save(todo);
        return todo;
    }


}

