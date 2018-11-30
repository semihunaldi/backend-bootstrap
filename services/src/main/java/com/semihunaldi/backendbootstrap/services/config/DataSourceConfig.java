package com.semihunaldi.backendbootstrap.services.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by semihunaldi on 30.11.2018
 */

@Configuration
public class DataSourceConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment env;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@Primary
	public DataSource dataSource() {
		DataSource dataSource = null;
		if(Lists.newArrayList(env.getActiveProfiles()).contains("dev")){
			String dataSourceUrl = env.getProperty("spring.datasource.url");
			if(StringUtils.isNotBlank(dataSourceUrl)){
				String dbName = dataSourceUrl.substring(dataSourceUrl.lastIndexOf(":") + 1, dataSourceUrl.length());
				dataSource = DataSourceBuilder.create()
						.url("jdbc:h2:mem:" + dbName)
						.username(env.getProperty("spring.datasource.username"))
						.password(env.getProperty("spring.datasource.password"))
						.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
						.build();
			}
		} else{
			dataSource = DataSourceBuilder
					.create()
					.url(env.getProperty("spring.datasource.url"))
					.username(env.getProperty("spring.datasource.username"))
					.password(env.getProperty("spring.datasource.password"))
					.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
					.build();
		}
		if(dataSource != null) {
			tryToConnectTwoTimes(dataSource);
		}
		return dataSource;
	}

	private void tryToConnectTwoTimes(DataSource dataSource) {
		try{
			dataSource.getConnection();
		} catch(SQLException e){
			try{
				logger.error("first try of db connection failed, waiting for 20 secs.");
				TimeUnit.SECONDS.sleep(20);
			} catch(InterruptedException e1){
				throw new RuntimeException(e1);
			}
			try{
				logger.error("second try of db connection started");
				dataSource.getConnection();
			} catch(SQLException e1){
				throw new RuntimeException(e1);
			}
		}
	}
}