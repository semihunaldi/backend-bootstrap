package com.semihunaldi.backendbootstrap.authserver;

import com.google.common.collect.ImmutableMap;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.JwtAuthenticationResponse;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.LoginRequest;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.SignUpRequest;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.SignUpResponse;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserProfile;
import com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model.UserSummary;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

/**
 * Created by semihunaldi on 29.11.2018
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {AuthorizationServerApplication.class})
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {EurekaClientAutoConfiguration.class})
@ActiveProfiles(value = {"default-test", "dev-test", "jwt"})
public class JWTAuthControllerTest {

	@Autowired
	protected MockMvc mvc;

	protected static TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testRegisterUserAndLogIn_RestTemplate() {
		registerUser();
		String jwtToken = loginUser();
		checkProfile(jwtToken);
		getUserProfile(jwtToken);
	}

	private void registerUser() {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmail("test1@test1.com");
		signUpRequest.setBirthDate(new Date());
		signUpRequest.setName("test");
		signUpRequest.setUserName("test1");
		signUpRequest.setPassword("password");
		signUpRequest.setMobilePhone("2342342342");
		ResponseEntity<SignUpResponse> responseEntity = restTemplate.postForEntity("http://localhost:8081/api/auth/signup", signUpRequest, SignUpResponse.class);
		Assert.assertNotNull(responseEntity);
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(StringUtils.isNotBlank(responseEntity.getBody().getId()));
	}

	private String loginUser() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsernameOrEmail("test1@test1.com");
		loginRequest.setPassword("password");
		ResponseEntity<JwtAuthenticationResponse> responseEntity = restTemplate.postForEntity("http://localhost:8081/api/auth/signin", loginRequest, JwtAuthenticationResponse.class);
		Assert.assertNotNull(responseEntity);
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(StringUtils.isNotBlank(responseEntity.getBody().getAccessToken()));
		return responseEntity.getBody().getAccessToken();
	}

	private void checkProfile(String jwtToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + jwtToken);
		ResponseEntity<UserSummary> exchange = restTemplate.exchange("http://localhost:8081/api/user/me", HttpMethod.GET, new HttpEntity<>(headers), UserSummary.class);
		Assert.assertNotNull(exchange);
		Assert.assertNotNull(exchange.getBody());
		Assert.assertTrue(StringUtils.isNotBlank(exchange.getBody().getId()));
		Assert.assertEquals("test", exchange.getBody().getName());
	}

	private void getUserProfile(String jwtToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + jwtToken);
		HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
		ResponseEntity<UserProfile> test = restTemplate.exchange("http://localhost:8081/api/users/{userName}", HttpMethod.GET, httpEntity, UserProfile.class, ImmutableMap.of("userName","test1"));
		Assert.assertNotNull(test);
		Assert.assertNotNull(test.getBody());
		Assert.assertTrue(StringUtils.isNotBlank(test.getBody().getId()));
		Assert.assertEquals("test", test.getBody().getName());
		Assert.assertEquals("test1", test.getBody().getUserName());
	}
}
