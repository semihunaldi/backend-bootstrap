package com.semihunaldi.backendbootstrap.ws.config.jwt;

import com.google.common.base.Predicate;
import com.semihunaldi.backendbootstrap.ws.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by semihunaldi on 25.11.2018
 */

@EnableSwagger2
@Configuration
@Profile("jwt")
public class SwaggerConfig {

	@Autowired
	private AppProperties appProperties;

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("backend-bootstrap-api")
				.apiInfo(apiInfo())
				.select()
				.paths(postPaths())
				.build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api/user.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Backend Bootstrap API")
				.description("Backend Bootstrap API reference for developers")
				.termsOfServiceUrl(appProperties.getDeveloperUrl())
				.contact(new Contact(appProperties.getDeveloperName(), appProperties.getDeveloperUrl(), appProperties.getDeveloperMail()))
				.license("Backend Bootstrap API License")
				.licenseUrl(appProperties.getDeveloperUrl()).version("1.0").build();
	}

	//TODO: jwt auth swagger config
}