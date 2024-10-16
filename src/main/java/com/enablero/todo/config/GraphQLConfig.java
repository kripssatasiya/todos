//package com.enablero.todo.config;
//
//import com.enablero.todo.graphql.scalar.LocalDateTimeScalar;
//import graphql.GraphQL;
//import graphql.schema.GraphQLSchema;
//import graphql.schema.idl.RuntimeWiring;
//import graphql.schema.idl.SchemaGenerator;
//import graphql.schema.idl.SchemaParser;
//import graphql.schema.idl.TypeDefinitionRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.graphql.execution.GraphQlSource;
//
//import java.io.File;
//
//@Configuration
//public class GraphQLConfig {
//
//    @Bean
//    public GraphQlSource graphQlSource() {
//        String schema = "type Query { hello: String }";
//        TypeDefinitionRegistry registry = new SchemaParser().parse(schema);
//
//        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
//                .scalar(LocalDateTimeScalar.INSTANCE)
//                .build();
//
//        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
//
//        return GraphQlSource.builder(graphQLSchema).build();
//    }
//}
