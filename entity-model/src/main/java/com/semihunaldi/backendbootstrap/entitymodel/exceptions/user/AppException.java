package com.semihunaldi.backendbootstrap.entitymodel.exceptions.user;

import com.semihunaldi.backendbootstrap.entitymodel.exceptions.BaseException;

/**
 * Created by semihunaldi on 29.11.2018
 */
public class AppException extends BaseException {

	public AppException() {
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int errorCode() {
		return -10;
	}

	@Override
	public String description() {
		return getMessage();
	}
}
