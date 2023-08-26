package com.masprogtechs.sales.application.system.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String dateString = p.getText();
        LocalDate localDate = LocalDate.parse(dateString, dateFormatter);
        return localDate.atStartOfDay();
    }
}
