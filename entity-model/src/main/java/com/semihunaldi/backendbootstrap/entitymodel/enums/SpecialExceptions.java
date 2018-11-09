package com.semihunaldi.backendbootstrap.entitymodel.enums;

/**
 * Created by semih on 23.08.2016.
 */
public enum SpecialExceptions {
	USER_NOT_FOUND_EXCEPTION(1, "User not found"),
	USER_EXISTS_EXCEPTION(2, "User exists with given data"),
	ERROR(99, "Error");

	private Integer errorCode;

	private String errorDescription;

	SpecialExceptions(Integer errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

}
