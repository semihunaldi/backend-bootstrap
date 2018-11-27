package com.semihunaldi.backendbootstrap.authserver.config.oauth2;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by semihunaldi on 21.11.2018
 */
@Configuration
@EnableWebSecurity
@Profile("oauth2")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Bean
	@Override
	@Profile("oauth2")
	public UserDetailsService userDetailsServiceBean() {
		return new CustomJdbcUserDetailsService();
	}

	@Override
	public void configure(WebSecurity web) {
		if(Lists.newArrayList(env.getActiveProfiles()).contains("dev")){
			web.ignoring().antMatchers("/webjars/**", "/h2/**");
		} else{
			web.ignoring().antMatchers("/webjars/**");
		}
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login", "logout.do").permitAll()
				.antMatchers("/**").authenticated()
				.and()
				.formLogin()
				.loginProcessingUrl("/login.do")
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/login")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"))
				.and()
				.userDetailsService(userDetailsServiceBean());
		http.headers().frameOptions().sameOrigin();
	}
}
