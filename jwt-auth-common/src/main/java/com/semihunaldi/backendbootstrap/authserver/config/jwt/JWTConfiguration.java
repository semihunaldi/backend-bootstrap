package com.semihunaldi.backendbootstrap.authserver.config.jwt;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Configuration
@EnableConfigurationProperties(JWTProperties.class)
@Profile("jwt")
@EnableJpaRepositories(basePackages = "com.semihunaldi.backendbootstrap.authserver.*")
@EntityScan(basePackages = "com.semihunaldi.backendbootstrap.entitymodel")
@ComponentScan(basePackages = "com.semihunaldi.backendbootstrap")
public class JWTConfiguration {

}
