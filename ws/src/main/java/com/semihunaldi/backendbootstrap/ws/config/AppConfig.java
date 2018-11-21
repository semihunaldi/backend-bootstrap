package com.semihunaldi.backendbootstrap.ws.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.semihunaldi.backendbootstrap.services.*")
@EntityScan(basePackages = "com.semihunaldi.backendbootstrap.entitymodel")
@ComponentScan(basePackages = "com.semihunaldi.backendbootstrap")
@EnableMongoRepositories(basePackages = "com.semihunaldi.backendbootstrap.services.*")
@EnableConfigurationProperties(AppProperties.class)
@EnableEurekaClient
public class AppConfig {

}
