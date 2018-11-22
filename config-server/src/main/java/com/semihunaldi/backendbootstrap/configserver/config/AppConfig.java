package com.semihunaldi.backendbootstrap.configserver.config;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * Created by semihunaldi on 21.11.2018
 */

@Configuration
@EnableConfigServer
@EnableEurekaClient
public class AppConfig {

}
