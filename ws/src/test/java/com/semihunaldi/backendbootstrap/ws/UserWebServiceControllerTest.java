package com.semihunaldi.backendbootstrap.ws;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by semihunaldi on 29.11.2018
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {BackendBootstrapWSApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:application-default-test.yml", "classpath:application-dev.yml"})
@EnableAutoConfiguration(exclude = {EurekaClientAutoConfiguration.class})
public class UserWebServiceControllerTest {

	private static final String testToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5QjE4NTkxQS1DNTE4LTRDREUtQkEzQi05QURDMzE0NUQ2NzciLCJpYXQiOjE1NDM0Nzc1OTAsImV4cCI6MTU0NDA4MjM5MH0.MFMBHbkYkou206aECKt6hWnLUrD4ZFrQezJwQtyw0L9Ymo2BL0pi_wb6_J5LOKWxb6N1psD3w1v22C7wCjtHOg";
	@Autowired
	private MockMvc mvc;

	@Test
	public void test() throws Exception {
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
}
