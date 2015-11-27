package com.boole.index;

import com.boole.common.BooleCommonApplicationConfig;
import com.boole.index.config.BatchConfig;
import com.boole.index.config.BeanConfig;
import com.boole.index.config.ElasticSearchConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/27/15.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.boole.common.config",
        "com.boole.common.service",
        "com.boole.index.listener",
        "com.boole.index.processor",
        "com.boole.index.reader",
        "com.boole.index.service",
        "com.boole.index.writer"})
@Import({BooleCommonApplicationConfig.class,
        BatchConfig.class,
        ElasticSearchConfig.class})
public class IndexApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200")
                .multiThreaded(true)
                .gson(gson())
                .build());
        return factory.getObject();
    }


    @Bean
    Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
        return gsonBuilder.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(IndexApplicationConfig.class, args);
    }
}
