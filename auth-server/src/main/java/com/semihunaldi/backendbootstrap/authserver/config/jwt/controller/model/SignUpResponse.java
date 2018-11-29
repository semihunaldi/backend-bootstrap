package com.semihunaldi.backendbootstrap.authserver.config.jwt.controller.model;

import com.semihunaldi.backendbootstrap.exceptionhandling.model.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by semihunaldi on 29.11.2018
 */

@EqualsAndHashCode(callSuper = false,of = "id")
@Data
public class SignUpResponse extends BaseResult {

	private String id;

	private String name;

	private String userName;

	private String email;

	private String mobilePhone;

	private Date birthDate;
}