package com.semihunaldi.backendbootstrap.entitymodel.exceptions.user;

import com.semihunaldi.backendbootstrap.entitymodel.exceptions.BaseException;
import lombok.NoArgsConstructor;

/**
 * Created by semihunaldi on 28.11.2018
 */

@NoArgsConstructor
public class UnknownException extends BaseException {

	public UnknownException(String message) {
		super(message);
	}

	public UnknownException(Throwable cause) {
		super(cause);
	}

	public UnknownException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int errorCode() {
		return -99;
	}

	@Override
	public String description() {
		return "Unknown Exception";
	}
}
