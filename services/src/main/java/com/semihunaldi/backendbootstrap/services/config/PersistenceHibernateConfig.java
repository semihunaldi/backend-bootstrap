package com.semihunaldi.backendbootstrap.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * Created by semihunaldi on 28.11.2018
 */
@Configuration
public class PersistenceHibernateConfig{
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
}