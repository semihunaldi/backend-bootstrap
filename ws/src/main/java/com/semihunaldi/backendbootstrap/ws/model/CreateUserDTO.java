package com.semihunaldi.backendbootstrap.ws.model;

import com.semihunaldi.backendbootstrap.entitymodel.enums.SpecialExceptions;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by semihunaldi on 9.11.2018
 */

@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel(value="CreateUserDTO class", description="represents an object request for User")
public class CreateUserDTO extends BaseResult {

	private String userId;

	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	private String mobilePhone;

	private String facebookId;

	private String twitterId;

	private String instagramId;

	private String fcmTokenId;

	private Integer age;

	private String ipAddress;

	public CreateUserDTO(Integer errorCode, String errorDescription) {
		super(errorCode, errorDescription);
	}

	public CreateUserDTO(SpecialExceptions specialExceptions) {
		super(specialExceptions.getErrorCode(), specialExceptions.getErrorDescription());
	}

	public CreateUserDTO() {
	}

	public User createUserEntity() {
		User user = new User();
		setUserCommonFields(user, this);
		if(StringUtils.isNotBlank(this.ipAddress)){
			user.setCreateUserIP(this.ipAddress);
		}
		return user;
	}

	public static void updateUserEntityFields(User user, CreateUserDTO createUserDTO) {
		setUserCommonFields(user, createUserDTO);
	}

	private static void setUserCommonFields(User user, CreateUserDTO createUserDTO) {
		user.setFirstName(createUserDTO.getFirstName());
		user.setLastName(createUserDTO.getLastName());
		user.setUserName(createUserDTO.getUserName());
		user.setEmail(createUserDTO.getEmail());
		user.setMobilePhone(createUserDTO.getMobilePhone());
		user.setAge(createUserDTO.getAge());
		user.setTwitterId(createUserDTO.getTwitterId());
		user.setInstagramId(createUserDTO.getInstagramId());
		user.setFacebookId(createUserDTO.getFacebookId());
		user.setFcmTokenId(createUserDTO.getFcmTokenId());
		if(StringUtils.isNotBlank(createUserDTO.getUserName())){
			user.setId(createUserDTO.getUserId());
		}
		if(StringUtils.isNotBlank(createUserDTO.getIpAddress())){
			user.setUpdateUserIP(createUserDTO.getIpAddress());
		}
	}

	public static CreateUserDTO createCreateUserDTO(User user) {
		CreateUserDTO createUserDTO = new CreateUserDTO();
		createUserDTO.setFirstName(user.getFirstName());
		createUserDTO.setLastName(user.getLastName());
		createUserDTO.setUserName(user.getUserName());
		createUserDTO.setEmail(user.getEmail());
		createUserDTO.setMobilePhone(user.getMobilePhone());
		createUserDTO.setAge(user.getAge());
		createUserDTO.setTwitterId(user.getTwitterId());
		createUserDTO.setInstagramId(user.getInstagramId());
		createUserDTO.setFacebookId(user.getFacebookId());
		createUserDTO.setUserId(user.getId());
		createUserDTO.setIpAddress(user.getUpdateUserIP());
		createUserDTO.setFcmTokenId(user.getFcmTokenId());
		return createUserDTO;
	}
}