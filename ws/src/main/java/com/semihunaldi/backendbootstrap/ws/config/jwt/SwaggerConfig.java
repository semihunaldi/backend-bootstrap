package com.semihunaldi.backendbootstrap.ws.config.jwt;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.semihunaldi.backendbootstrap.ws.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

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

	@Autowired
	private JWTAuthProperties jwtAuthProperties;

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("backend-bootstrap-api")
				.apiInfo(apiInfo())
				.select()
				.paths(postPaths())
				.build()
				.securitySchemes(Collections.singletonList(securitySchema()))
				.securityContexts(Collections.singletonList(securityContext()));
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

	private ApiKey securitySchema() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(postPaths()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration(null, null, "", "JWT", ": Bearer", null, false);
	}
}