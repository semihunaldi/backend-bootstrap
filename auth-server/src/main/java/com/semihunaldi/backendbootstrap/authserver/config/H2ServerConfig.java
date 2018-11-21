package com.semihunaldi.backendbootstrap.authserver.config;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

/**
 * Created by semihunaldi on 21.11.2018
 */

@Configuration
@Profile("dev")
public class H2ServerConfig {
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		WebServlet webServlet = new WebServlet();
		ServletRegistrationBean bean = new ServletRegistrationBean<>(webServlet);
		bean.addUrlMappings("/h2/*");
		return bean;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}
