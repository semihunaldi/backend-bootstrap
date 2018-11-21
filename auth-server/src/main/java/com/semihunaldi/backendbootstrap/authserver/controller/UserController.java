package com.semihunaldi.backendbootstrap.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by semihunaldi on 21.11.2018
 */
@RestController
public class UserController {

	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
