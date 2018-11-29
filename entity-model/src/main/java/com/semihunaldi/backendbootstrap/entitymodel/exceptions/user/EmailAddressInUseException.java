package com.semihunaldi.backendbootstrap.entitymodel.exceptions.user;

import com.semihunaldi.backendbootstrap.entitymodel.exceptions.BaseException;

/**
 * Created by semihunaldi on 29.11.2018
 */
public class EmailAddressInUseException extends BaseException {

	public EmailAddressInUseException() {
		super();
	}

	public EmailAddressInUseException(String message) {
		super(message);
	}

	public EmailAddressInUseException(Throwable cause) {
		super(cause);
	}

	public EmailAddressInUseException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int errorCode() {
		return 3;
	}

	@Override
	public String description() {
		return "Email Address already in use";
	}
}
