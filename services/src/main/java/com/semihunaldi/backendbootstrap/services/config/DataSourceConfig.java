package com.semihunaldi.backendbootstrap.services.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by semihunaldi on 30.11.2018
 */

@Configuration
public class DataSourceConfig {

	@Autowired
	private Environment env;

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	@Primary
	public DataSource dataSource() {
		if(Lists.newArrayList(env.getActiveProfiles()).contains("dev")){
			String dataSourceUrl = env.getProperty("spring.datasource.url");
			if(StringUtils.isNotBlank(dataSourceUrl)){
				String dbName = dataSourceUrl.substring(dataSourceUrl.lastIndexOf(":") + 1, dataSourceUrl.length());
				return DataSourceBuilder.create()
						.url("jdbc:h2:mem:" + dbName)
						.username(env.getProperty("spring.datasource.username"))
						.password(env.getProperty("spring.datasource.password"))
						.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
						.build();
			}
		}
		return DataSourceBuilder
				.create()
				.build();
	}
}
