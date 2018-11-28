package com.semihunaldi.backendbootstrap.entitymodel.exceptions.user;

import com.semihunaldi.backendbootstrap.entitymodel.exceptions.BaseException;
import lombok.NoArgsConstructor;

/**
 * Created by semihunaldi on 28.11.2018
 */

@NoArgsConstructor
public class UserNotFoundException extends BaseException {

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

	@Override
	public int errorCode() {
		return 1;
	}

	@Override
	public String description() {
		return "User not found";
	}
}
