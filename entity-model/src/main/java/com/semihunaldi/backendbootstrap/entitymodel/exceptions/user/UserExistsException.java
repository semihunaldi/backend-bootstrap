package com.semihunaldi.backendbootstrap.entitymodel.exceptions.user;

import com.semihunaldi.backendbootstrap.entitymodel.exceptions.BaseException;
import lombok.NoArgsConstructor;

/**
 * Created by semihunaldi on 28.11.2018
 */

@NoArgsConstructor
public class UserExistsException extends BaseException {

	public UserExistsException(String message) {
		super(message);
	}

	public UserExistsException(Throwable cause) {
		super(cause);
	}

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int errorCode() {
		return 2;
	}

	@Override
	public String description() {
		return "User exists with given data";
	}
}
