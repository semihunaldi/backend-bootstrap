package com.semihunaldi.backendbootstrap.authserver;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

/**
 * Created by semihunaldi on 21.11.2018
 */
@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		WebServlet webServlet = new WebServlet();
		ServletRegistrationBean bean = new ServletRegistrationBean(webServlet);
		bean.addUrlMappings("/h2/*");
		return bean;
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}