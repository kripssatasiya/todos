package com.enablero.todo.repository;

import com.enablero.todo.entity.UserEntity;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface UserRepository  extends DynamoDBCrudRepository<UserEntity, String> {

}
