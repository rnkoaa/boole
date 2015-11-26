package com.boole.common;

import com.boole.common.domain.util.JSR310DateTimeSerializer;
import com.boole.common.domain.util.JSR310LocalDateDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import java.time.*;

/**
 * Created on 11/5/2015.
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.boole.common.config",
        "com.boole.common.domain",
        "com.boole.common.repository",
        "com.boole.common.service"})
public class BooleCommonApplicationConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(OffsetDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        module.addSerializer(LocalDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        module.addDeserializer(OffsetDateTime.class, InstantDeserializer.OFFSET_DATE_TIME);
        module.addSerializer(Instant.class, JSR310DateTimeSerializer.INSTANCE);
        module.addDeserializer(LocalDate.class, JSR310LocalDateDeserializer.INSTANCE);
        objectMapper.registerModule(module);
        return objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(BooleCommonApplicationConfig.class, args);
    }
}
