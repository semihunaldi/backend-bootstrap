package com.semihunaldi.backendbootstrap.uithymeleaf.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by semihunaldi on 6.12.2018
 */

@Data
public class LoginModel {

	@NotNull
	@Size(min=3, max=20)
	private String userName;

	@NotNull
	@Size(min=3, max=12)
	private String password;
}
