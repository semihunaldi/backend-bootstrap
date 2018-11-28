package com.semihunaldi.backendbootstrap.ws.model;

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

	private String name;

	private String userName;

	private String email;

	private String facebookId;

	private String twitterId;

	private String instagramId;

	public UserDTO() {
	}

	public static UserDTO create(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setUserName(user.getUserName());
		userDTO.setEmail(user.getEmail());
		userDTO.setTwitterId(user.getTwitterId());
		userDTO.setInstagramId(user.getInstagramId());
		userDTO.setFacebookId(user.getFacebookId());
		return userDTO;
	}
}
