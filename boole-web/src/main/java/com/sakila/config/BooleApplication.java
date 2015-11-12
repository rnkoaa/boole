package com.sakila.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.sakila.config",
        "com.sakila.repository",
        "com.sakila.service",
        "com.sakila.controller"
})
@EnableJpaRepositories(basePackages = "com.sakila.repository")
@EntityScan(basePackages = {"com.sakila.domain"})
@Import({IndexerConfig.class})
public class BooleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooleApplication.class, args);
    }

}
