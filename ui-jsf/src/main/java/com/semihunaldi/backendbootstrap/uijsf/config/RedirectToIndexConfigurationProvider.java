package com.semihunaldi.backendbootstrap.uijsf.config;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.servlet.ServletContext;

/**
 * Created by semihunaldi on 24.11.2018
 */

@Component
@ApplicationScope
public class RedirectToIndexConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public Configuration getConfiguration(ServletContext t) {
		return ConfigurationBuilder.begin()
			.addRule()
			.when(Direction.isInbound().and(Path.matches("/")))
			.perform(Redirect.temporary("/index.xhtml"));
	}

	@Override
	public int priority() {
		return 10;
	}

}
