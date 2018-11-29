package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import com.semihunaldi.backendbootstrap.exceptionhandling.model.BaseResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = false, of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile extends BaseResult {

	private String id;
	private String name;
	private String userName;
	private Date joinedAt;
	private String email;
	private String mobilePhone;
	private Date birthDate;
}
