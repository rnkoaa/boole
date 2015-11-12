package com.boole.common.config;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created on 11/12/2015.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.boole.common.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.boole.common.domain"})
@EnableTransactionManagement
public class RepositoryConfig {

    private final Logger log = LoggerFactory.getLogger(RepositoryConfig.class);
    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        if (dataSourceProperties.getUrl() == null) {
            log.error("Your database connection pool configuration is incorrect! The application" +
                            " cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(dataSourceProperties.getDriverClassName());
        config.addDataSourceProperty("url", dataSourceProperties.getUrl());
        config.setDataSourceClassName(dataSourceProperties.getDriverClassName());
        config.addDataSourceProperty("url", dataSourceProperties.getUrl());
        if (dataSourceProperties.getUsername() != null) {
            config.addDataSourceProperty("user", dataSourceProperties.getUsername());
        } else {
            config.addDataSourceProperty("user", ""); // HikariCP doesn't allow null user
        }
        if (dataSourceProperties.getPassword() != null) {
            config.addDataSourceProperty("password", dataSourceProperties.getPassword());
        } else {
            config.addDataSourceProperty("password", ""); // HikariCP doesn't allow null password
        }

        return new HikariDataSource(config);
    }

    @Bean
    public Hibernate4Module hibernate4Module() {
        return new Hibernate4Module();
    }

}