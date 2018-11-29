package com.semihunaldi.backendbootstrap.exceptionhandling.model;

import lombok.Data;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Data
public class BaseResult implements IBaseResult {

	private ErrorMessage errorMessage = null;

	private Integer errorCode = 0;

	private String errorDescription = "Success";

	public BaseResult() {
	}

	public BaseResult(int errorCode, String errorDescription, ErrorMessage errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
	}
}
