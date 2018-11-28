package com.semihunaldi.backendbootstrap.ws.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage implements Serializable {
	private String message;
	private String localizedMessage;
	private String exceptionClass;
	private String contextPath;
	private String uri;
}
