package com.semihunaldi.backendbootstrap.scheduler.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.semihunaldi.backendbootstrap.services.*")
@EntityScan(basePackages = "com.semihunaldi.backendbootstrap.entitymodel")
@ComponentScan(basePackages = "com.semihunaldi.backendbootstrap")
@EnableMongoRepositories(basePackages = "com.semihunaldi.backendbootstrap.services.*")
@EnableEurekaClient
public class AppConfig {

}
