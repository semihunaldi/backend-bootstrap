package com.semihunaldi.backendbootstrap.ws.model;

import lombok.Data;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Data
public class ErrorMessage {
	private String message;
	private String localizedMessage;
	private String exceptionClass;
	private String contextPath;
	private String uri;
}
