package com.semihunaldi.backendbootstrap.services.config.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;
import java.util.Properties;

/**
 * Created by semihunaldi on 2.12.2018
 */

@Configuration
public class GmailMailSenderConfig {

	@Autowired
	private Environment env;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("spring.mail.host"));
		mailSender.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.mail.port"))));

		mailSender.setUsername(env.getProperty("spring.mail.username"));
		mailSender.setPassword(env.getProperty("spring.mail.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", Objects.requireNonNull(env.getProperty("spring.mail.protocol")));
		props.put("mail.smtp.auth", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.smtp.auth")));
		props.put("mail.smtp.starttls.enable", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.smtp.starttls.enable")));
		props.put("mail.debug", Objects.requireNonNull(env.getProperty("spring.mail.properties.mail.debug")));

		return mailSender;
	}
}
