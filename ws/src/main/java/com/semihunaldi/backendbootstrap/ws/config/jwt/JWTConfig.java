package com.semihunaldi.backendbootstrap.ws.config.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Configuration
@EnableConfigurationProperties(JWTAuthProperties.class)
@Profile("jwt")
public class JWTConfig {

}
