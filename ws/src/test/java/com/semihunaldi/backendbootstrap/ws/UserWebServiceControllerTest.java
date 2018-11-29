package com.semihunaldi.backendbootstrap.ws;

import com.google.common.collect.ImmutableMap;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserNotFoundException;
import com.semihunaldi.backendbootstrap.ws.model.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by semihunaldi on 29.11.2018
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {BackendBootstrapWSApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:application-default-test.yml", "classpath:application-dev.yml"})
@EnableAutoConfiguration(exclude = {EurekaClientAutoConfiguration.class})
public class UserWebServiceControllerTest {

	private static final String testToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5QjE4NTkxQS1DNTE4LTRDREUtQkEzQi05QURDMzE0NUQ2NzciLCJpYXQiOjE1NDM0Nzc1OTAsImV4cCI6MTU0NDA4MjM5MH0.MFMBHbkYkou206aECKt6hWnLUrD4ZFrQezJwQtyw0L9Ymo2BL0pi_wb6_J5LOKWxb6N1psD3w1v22C7wCjtHOg";

	@Autowired
	private MockMvc mvc;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@PostConstruct
	public void init() {
		restTemplate.getRestTemplate().setInterceptors(
				Collections.singletonList((request, body, execution) -> {
					request.getHeaders()
							.add("Authorization", testToken);
					return execution.execute(request, body);
				}));
	}

	@Test
	public void testQueryUserByEmailMockMvc() throws Exception {
		mvc.perform(
				get("/api/user/queryUserByEmail")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", testToken)
						.param("email", "test@test.com")
		).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Success")))
				.andExpect(content().string(containsString("test@test.com")));
	}

	@Test
	public void testQueryUserByEmailRestTemplate() {
		ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/user/queryUserByEmail?email={email}", UserDTO.class, ImmutableMap.of("email", "test@test.com"));
		UserDTO body = responseEntity.getBody();
		Assert.assertNotNull(body);
		Assert.assertEquals("test@test.com", body.getEmail());
	}

	@Test
	public void testQueryUserByEmailUserNotExists() {
		ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/user/queryUserByEmail?email={email}", UserDTO.class, ImmutableMap.of("email", "notexists@test.com"));
		UserDTO body = responseEntity.getBody();
		Assert.assertNotNull(body);
		Assert.assertEquals(body.getErrorMessage().getExceptionClass(), UserNotFoundException.class.getCanonicalName());
	}
}
