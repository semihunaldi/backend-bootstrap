package com.semihunaldi.backendbootstrap.ws.config.oauth2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

/**
 * Created by semihunaldi on 27.11.2018
 */

@ConfigurationProperties(prefix = "app.properties")
@Data
@Profile("oauth2")
public class Oauth2Properties {
	private String authServer;
	private String clientId;
	private String clientSecret;
}
