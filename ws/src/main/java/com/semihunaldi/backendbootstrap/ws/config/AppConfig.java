package com.semihunaldi.backendbootstrap.ws.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.semihunaldi.backendbootstrap.services.*")
@EntityScan(basePackages = "com.semihunaldi.backendbootstrap.entitymodel")
@ComponentScan(basePackages = "com.semihunaldi.backendbootstrap")
public class AppConfig {

}