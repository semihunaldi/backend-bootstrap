package com.semihunaldi.backendbootstrap.ws;

import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

/**
 * Created by semihunaldi on 29.11.2018
 */

public abstract class BaseWebServiceControllerTest {

	protected static final String testToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5QjE4NTkxQS1DNTE4LTRDREUtQkEzQi05QURDMzE0NUQ2NzciLCJpYXQiOjE1NDM0Nzc1OTAsImV4cCI6MTU0NDA4MjM5MH0.MFMBHbkYkou206aECKt6hWnLUrD4ZFrQezJwQtyw0L9Ymo2BL0pi_wb6_J5LOKWxb6N1psD3w1v22C7wCjtHOg";

	@Autowired
	protected MockMvc mvc;

	protected static TestRestTemplate restTemplate = new TestRestTemplate();

	@BeforeClass
	public static void init() {
		restTemplate.getRestTemplate().setInterceptors(
				Collections.singletonList((request, body, execution) -> {
					request.getHeaders()
							.add("Authorization", testToken);
					return execution.execute(request, body);
				}));
	}
}
