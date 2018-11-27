package com.semihunaldi.backendbootstrap.authserver.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

/**
 * Created by semih on 9.09.2016.
 */

@ConfigurationProperties(prefix = "app.properties")
@Data
@Profile("jwt")
public class JWTProperties {

	private String jwtSecret;
	private Long jwtExpirationInMs;
}
