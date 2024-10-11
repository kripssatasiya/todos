package com.enablero.todo.graphql.scalar;

import graphql.language.StringValue;
import graphql.schema.GraphQLScalarType;
import graphql.schema.Coercing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeScalar {

    public static final GraphQLScalarType INSTANCE = GraphQLScalarType.newScalar()
            .name("LocalDateTime")
            .description("A custom scalar for LocalDateTime")
            .coercing(new Coercing<LocalDateTime, String>() {
                private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                @Override
                public String serialize(Object dataFetcherResult) {
                    return ((LocalDateTime) dataFetcherResult).format(formatter);
                }

                @Override
                public LocalDateTime parseValue(Object input) {
                    return LocalDateTime.parse((String) input, formatter);
                }

                @Override
                public LocalDateTime parseLiteral(Object input) {
                    if (input instanceof StringValue) {
                        return LocalDateTime.parse(((StringValue) input).getValue(), formatter);
                    }
                    return null;
                }
            })
            .build();
}


