package com.enablero.todo.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // LocalDateTime to String (for storing in DynamoDB)
    @Override
    public String convert(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(formatter) : null;
    }

    // String back to LocalDateTime (when retrieving from DynamoDB)
    @Override
    public LocalDateTime unconvert(String string) {
        return string != null ? LocalDateTime.parse(string, formatter) : null;
    }
}