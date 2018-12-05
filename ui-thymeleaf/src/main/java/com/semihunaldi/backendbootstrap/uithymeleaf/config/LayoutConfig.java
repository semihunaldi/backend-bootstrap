package com.semihunaldi.backendbootstrap.uithymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

/**
 * Created by semihunaldi on 5.12.2018
 */

@Configuration
public class LayoutConfig {

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

	@EventListener(ApplicationStartedEvent.class)
	public void templateEngine() {
		springTemplateEngine.addTemplateResolver(new UrlTemplateResolver());
	}
}
