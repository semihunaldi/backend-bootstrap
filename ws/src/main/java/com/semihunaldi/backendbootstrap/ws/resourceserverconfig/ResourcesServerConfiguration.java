package com.semihunaldi.backendbootstrap.ws.resourceserverconfig;

import com.semihunaldi.backendbootstrap.ws.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Created by semihunaldi on 21.11.2018
 */
@Configuration
@EnableResourceServer
public class ResourcesServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private DataSource dataSource;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
//		resources.resourceId(appProperties.getResourceId()).tokenServices(remoteTokenServices());
		resources.resourceId(appProperties.getResourceId()).tokenStore(tokenStore());
	}

//	@Bean
//	public RemoteTokenServices remoteTokenServices() {
//		final RemoteTokenServices tokenServices = new RemoteTokenServices();
//		String checkTokenUri = "http://localhost:8081/oauth/check_token";
//		tokenServices.setCheckTokenEndpointUrl(checkTokenUri);
//		tokenServices.setClientId("curl_client");
//		tokenServices.setClientSecret("password");
//		return tokenServices;
//	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
				.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
				.antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
				.and()
				.headers().addHeaderWriter((request, response) -> {
			response.addHeader("Access-Control-Allow-Origin", "*");
			if(request.getMethod().equals("OPTIONS")){
				response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
				response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
			}
		});
	}
}
