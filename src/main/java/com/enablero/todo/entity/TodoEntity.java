package com.enablero.todo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.enablero.todo.converter.LocalDateTimeConverter;
import com.enablero.todo.model.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "todo")
public class TodoEntity {


    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "email")
    private String email;

    @DynamoDBAttribute(attributeName = "title")
    private String title;

    @DynamoDBAttribute(attributeName = "description")
    private String description;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "status")
    private TodoStatus status;

    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    @DynamoDBAttribute(attributeName = "createdDt")
    private LocalDateTime createdDt;

    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    @DynamoDBAttribute(attributeName = "updateDt")
    private LocalDateTime updateDt;
}
