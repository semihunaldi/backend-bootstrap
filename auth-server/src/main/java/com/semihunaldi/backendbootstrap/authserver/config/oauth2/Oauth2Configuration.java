package com.semihunaldi.backendbootstrap.authserver.config.oauth2;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Configuration
@Profile("oauth2")
@EnableJpaRepositories(basePackages = "com.semihunaldi.backendbootstrap.authserver.config.oauth2.*")
@EntityScan(basePackages = {"com.semihunaldi.backendbootstrap.entitymodel","com.semihunaldi.backendbootstrap.authserver.config.oauth2.model"})
@ComponentScan(basePackages = "com.semihunaldi.backendbootstrap")
public class Oauth2Configuration {

}
