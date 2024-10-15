package com.enablero.todo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		checkDynamoDBConfiguration();
	}

	private void checkDynamoDBConfiguration() {
		try {
			amazonDynamoDB.listTables();
			System.out.println("DynamoDB is configured and accessible.");
		} catch (Exception e) {
			System.err.println("DynamoDB configuration failed: " + e.getMessage());
		}
	}

	// java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

}
