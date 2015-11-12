package com.boole.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
   /* @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                false);
        objectMapper.registerModule(new JodaModule());
        return objectMapper;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(BooleCommonApplicationConfig.class, args);
    }
}
