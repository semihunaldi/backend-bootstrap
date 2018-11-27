package com.semihunaldi.backendbootstrap.ws.config.oauth2;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Configuration
@EnableConfigurationProperties(Oauth2Properties.class)
@Profile("oauth2")
public class Oauth2Config {

}
