package com.semihunaldi.backendbootstrap.scheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Configuration
@EnableAutoConfiguration
public class QuartzConfig {

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Bean
	@QuartzDataSource
	public DataSource quartzDataSource() {
		return DataSourceBuilder.create()
				.url(dataSourceProperties.getUrl())
				.driverClassName(dataSourceProperties.getDriverClassName())
				.username(dataSourceProperties.getUsername())
				.password(dataSourceProperties.getPassword())
				.type(dataSourceProperties.getType())
				.build();
	}
}
