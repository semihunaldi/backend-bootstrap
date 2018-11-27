package com.semihunaldi.backendbootstrap.jwtauthserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by semih on 9.09.2016.
 */

@ConfigurationProperties(prefix = "app.properties")
@Data
public class AppProperties {

	private String jwtSecret;
	private Long jwtExpirationInMs;
}
