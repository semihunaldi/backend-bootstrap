package com.semihunaldi.backendbootstrap.ws;

import com.google.common.collect.ImmutableMap;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserNotFoundException;
import com.semihunaldi.backendbootstrap.ws.model.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by semihunaldi on 29.11.2018
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {WSApplication.class})
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {EurekaClientAutoConfiguration.class})
@ActiveProfiles(value = {"default-test", "dev-test", "dev", "jwt"})
public class UserWebServiceControllerTest extends BaseWebServiceControllerTest {

	@Test
	public void testQueryUserByEmail_MockMvc() throws Exception {
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
	public void testQueryUserByEmail_RestTemplate() {
		ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/user/queryUserByEmail?email={email}", UserDTO.class, ImmutableMap.of("email", "test@test.com"));
		UserDTO body = responseEntity.getBody();
		Assert.assertNotNull(body);
		Assert.assertEquals("test@test.com", body.getEmail());
	}

	@Test
	public void testQueryUserByEmail_UserNotExists() {
		ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/user/queryUserByEmail?email={email}", UserDTO.class, ImmutableMap.of("email", "notexists@test.com"));
		UserDTO body = responseEntity.getBody();
		Assert.assertNotNull(body);
		Assert.assertEquals(body.getErrorMessage().getExceptionClass(), UserNotFoundException.class.getCanonicalName());
	}
}
