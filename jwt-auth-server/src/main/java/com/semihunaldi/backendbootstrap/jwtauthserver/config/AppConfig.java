package com.semihunaldi.backendbootstrap.jwtauthserver.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Configuration
@EnableConfigurationProperties(AppProperties.class)
@EnableJpaRepositories(basePackages = "com.semihunaldi.backendbootstrap.jwtauthserver.*")
@EntityScan(basePackages = "com.semihunaldi.backendbootstrap.entitymodel")
@ComponentScan(basePackages = "com.semihunaldi.backendbootstrap")
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class AppConfig {

}
