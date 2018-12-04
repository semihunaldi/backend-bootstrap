package com.semihunaldi.backendbootstrap.zuulserver.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

/**
 * Created by semihunaldi on 4.12.2018
 */

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulConfig {

}
