package com.semihunaldi.backendbootstrap.entitymodel.exceptions;

/**
 * Created by semihunaldi on 28.11.2018
 */
public abstract class BaseException extends RuntimeException implements GenericException {

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
