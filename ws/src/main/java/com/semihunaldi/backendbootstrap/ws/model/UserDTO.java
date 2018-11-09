package com.semihunaldi.backendbootstrap.ws.model;

import com.semihunaldi.backendbootstrap.entitymodel.enums.SpecialExceptions;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by semihunaldi on 9.11.2018
 */

@EqualsAndHashCode(callSuper = false)
@Data
public class UserDTO extends BaseResult {

	private String id;

	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	private String facebookId;

	private String twitterId;

	private String instagramId;

	public UserDTO(Integer errorCode, String errorDescription) {
		super(errorCode, errorDescription);
	}

	public UserDTO(SpecialExceptions specialExceptions) {
		super(specialExceptions.getErrorCode(), specialExceptions.getErrorDescription());
	}

	public UserDTO() {
	}

	public static UserDTO create(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setUserName(user.getUserName());
		userDTO.setEmail(user.getEmail());
		userDTO.setTwitterId(user.getTwitterId());
		userDTO.setInstagramId(user.getInstagramId());
		userDTO.setFacebookId(user.getFacebookId());
		return userDTO;
	}
}
