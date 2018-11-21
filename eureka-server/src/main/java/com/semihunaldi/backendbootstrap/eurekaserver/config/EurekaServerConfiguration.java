package com.semihunaldi.backendbootstrap.eurekaserver.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by semihunaldi on 21.11.2018
 */

@Configuration
@EnableAutoConfiguration
@EnableEurekaServer
public class EurekaServerConfiguration {

}
