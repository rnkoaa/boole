package com.boole.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.boole.config",
        "com.boole.repository",
        "com.boole.common.service",
        "com.boole.controller"
})
@EnableJpaRepositories(basePackages = "com.boole.repository")
@EntityScan(basePackages = {"com.boole.domain"})
@Import({IndexerConfig.class})
public class BooleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooleApplication.class, args);
    }

}
