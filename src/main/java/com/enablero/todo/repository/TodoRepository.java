package com.enablero.todo.repository;

import com.enablero.todo.entity.TodoEntity;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableScan
public interface TodoRepository extends DynamoDBCrudRepository<TodoEntity , String> {
   List<TodoEntity> findByEmail(String email);
}

