package com.boole.web.config;

import com.boole.common.BooleCommonApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.boole.web.config",
        "com.boole.common.service",
        "com.boole.web.controller"
})
@Import({BooleCommonApplicationConfig.class})
public class BooleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooleApplication.class, args);
    }

}
