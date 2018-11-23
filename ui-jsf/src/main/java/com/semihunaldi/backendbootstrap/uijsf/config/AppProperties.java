package com.semihunaldi.backendbootstrap.uijsf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by semihunaldi on 23.11.2018
 */

@ConfigurationProperties(prefix = "app.properties")
@Data
public class AppProperties {

	private String testProperty;
}
