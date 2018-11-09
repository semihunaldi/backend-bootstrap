package com.semihunaldi.backendbootstrap.entitymodel.exceptions;

import com.semihunaldi.backendbootstrap.entitymodel.enums.SpecialExceptions;

/**
 * Created by semih on 13.09.2016.
 */
public class UserException extends RuntimeException {

	private Integer errorCode;

	private String errorDescription;

	public UserException(Integer errorCode, String errorDescription) {
		super(errorDescription);
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public UserException(SpecialExceptions specialExceptions) {
		super(specialExceptions.getErrorDescription());
		this.errorCode = specialExceptions.getErrorCode();
		this.errorDescription = specialExceptions.getErrorDescription();
	}

	@Override
	public String toString() {
		return super.getMessage().concat(" ").concat(errorCode.toString()).concat(" ").concat(errorDescription);
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}
}