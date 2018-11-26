package com.semihunaldi.backendbootstrap.ws.config;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by semihunaldi on 25.11.2018
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Autowired
	private AppProperties appProperties;

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

	private OAuth securitySchema() {

		List<AuthorizationScope> authorizationScopeList = newArrayList();
		authorizationScopeList.add(new AuthorizationScope("read", "read all"));
		authorizationScopeList.add(new AuthorizationScope("write", "access all"));

		List<GrantType> grantTypes = newArrayList();
		GrantType creGrant = new ClientCredentialsGrant(appProperties.getAuthServer() + "/oauth/token");

		grantTypes.add(creGrant);

		return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(postPaths()).build();
	}

	private List<SecurityReference> defaultAuth() {

		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
		authorizationScopes[0] = new AuthorizationScope("read", "read all");
		authorizationScopes[1] = new AuthorizationScope("write", "write all");

		return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		//		new SecurityConfiguration(appProperties.getClientId(), appProperties.getClientSecret(), "", "", "", ApiKeyVehicle.HEADER, "Authorization", ": Bearer");
		return new SecurityConfiguration(appProperties.getClientId(), appProperties.getClientSecret(), "", "", ": Bearer", null, true);
	}
}