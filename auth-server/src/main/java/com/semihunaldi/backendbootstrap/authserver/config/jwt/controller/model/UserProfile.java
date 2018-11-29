package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {

	private String id;
	private String name;
	private String userName;
	private Date joinedAt;
	private String email;
	private String mobilePhone;
	private Date birthDate;
}
