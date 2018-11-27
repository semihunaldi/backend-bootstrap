package com.semihunaldi.backendbootstrap.ws.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

/**
 * Created by semihunaldi on 27.11.2018
 */

@ConfigurationProperties(prefix = "app.properties")
@Data
@Profile("jwt")
public class JWTAuthProperties {

	private String authServer;
}
