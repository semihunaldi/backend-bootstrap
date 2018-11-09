package com.semihunaldi.backendbootstrap.ws.model;

import lombok.Data;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Data
public class BaseResult {

	private Integer errorCode = 0;

	private String errorDescription = "Success";

	public BaseResult(Integer errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public BaseResult() {
	}
}
