package com.semihunaldi.backendbootstrap.ws.model;

import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import com.semihunaldi.backendbootstrap.exceptionhandling.model.BaseResult;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by semihunaldi on 9.11.2018
 */

@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel(value = "CreateUserDTO class", description = "represents an object request for User")
public class CreateUserDTO extends BaseResult {

	private String userId;

	@NotBlank
	@Size(min = 4, max = 40)
	private String name;

	@NotBlank
	@Size(min = 3, max = 15)
	private String userName;

	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	private String mobilePhone;

	private String facebookId;

	private String twitterId;

	private String instagramId;

	private String fcmTokenId;

	@NotNull
	private Date birthDate;

	private String ipAddress;

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
		user.setName(createUserDTO.getName());
		user.setUserName(createUserDTO.getUserName());
		user.setEmail(createUserDTO.getEmail());
		user.setMobilePhone(createUserDTO.getMobilePhone());
		user.setBirthDate(createUserDTO.getBirthDate());
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
		createUserDTO.setName(user.getName());
		createUserDTO.setUserName(user.getUserName());
		createUserDTO.setEmail(user.getEmail());
		createUserDTO.setMobilePhone(user.getMobilePhone());
		createUserDTO.setBirthDate(user.getBirthDate());
		createUserDTO.setTwitterId(user.getTwitterId());
		createUserDTO.setInstagramId(user.getInstagramId());
		createUserDTO.setFacebookId(user.getFacebookId());
		createUserDTO.setUserId(user.getId());
		createUserDTO.setIpAddress(user.getUpdateUserIP());
		createUserDTO.setFcmTokenId(user.getFcmTokenId());
		return createUserDTO;
	}
}