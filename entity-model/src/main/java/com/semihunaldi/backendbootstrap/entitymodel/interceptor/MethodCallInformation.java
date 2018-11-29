package com.semihunaldi.backendbootstrap.entitymodel.interceptor;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by semihunaldi on 29.11.2018
 */

@Data
public class MethodCallInformation implements Serializable
{
	@NotBlank
	private String username = "SYSTEM";
	@NotBlank
	private String ip = "127.0.0.1";
	@NotBlank
	private String uniqueId;


	private MethodCallInformation()
	{
	}

	public static MethodCallInformation instanceOf(String username, String ip, String uniqueId)
	{
		MethodCallInformation methodCallInformation = new MethodCallInformation();
		methodCallInformation.setUsername(username);
		methodCallInformation.setIp(ip);
		methodCallInformation.setUniqueId(uniqueId);
		return methodCallInformation;
	}

	public static MethodCallInformation instanceOf()
	{
		MethodCallInformation methodCallInformation = new MethodCallInformation();
		methodCallInformation.setUsername("SYSTEM");
		methodCallInformation.setIp("127.0.0.1");
		methodCallInformation.setUniqueId("11111");
		return methodCallInformation;
	}
}

