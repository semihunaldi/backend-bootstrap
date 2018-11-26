package com.semihunaldi.backendbootstrap.uijsf.config;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by semihunaldi on 26.11.2018
 */

@Configuration
public class ErrorConfig {

	static final String PAGE_PREFIX = "/customErrors/";

	@Bean
	public ErrorViewResolver containerCustomizer() {
		return new MyErrorViewResolver();
	}

	private static class MyErrorViewResolver implements ErrorViewResolver {

		@Override
		public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
			return new ModelAndView(PAGE_PREFIX + "errorOccured.xhtml");
		}
	}
}
