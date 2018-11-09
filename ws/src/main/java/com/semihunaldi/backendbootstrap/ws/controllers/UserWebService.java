package com.semihunaldi.backendbootstrap.ws.controllers;

import com.semihunaldi.backendbootstrap.ws.model.CreateUserDTO;
import com.semihunaldi.backendbootstrap.ws.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by semihunaldi on 9.11.2018
 */

@RequestMapping(path = "/api")
public interface UserWebService {

	@RequestMapping(value = "/user/queryUserByEmail", method = {RequestMethod.GET})
	UserDTO queryUserByEmail(@RequestParam(value = "email", defaultValue = "") String email);

	@RequestMapping(value = "/user/queryUserById", method = {RequestMethod.GET})
	UserDTO queryUserById(@RequestParam(value = "id", defaultValue = "") String id);

	@RequestMapping(value = "/user/queryUserByTwitterId", method = {RequestMethod.GET})
	UserDTO queryUserByTwitterId(@RequestParam(value = "twitterId", defaultValue = "") String twitterId);

	@RequestMapping(value = "/user/queryUserByUserName", method = {RequestMethod.GET})
	UserDTO queryUserByUserName(@RequestParam(value = "userName", defaultValue = "") String userName);

	@RequestMapping(value = "/user/queryUserByInstagramId", method = {RequestMethod.GET})
	UserDTO queryUserByInstagramId(@RequestParam(value = "instagramId", defaultValue = "") String instagramId);

	@RequestMapping(value = "/user/queryUserByFacebookId", method = {RequestMethod.GET})
	UserDTO queryUserByFacebookId(@RequestParam(value = "facebookId", defaultValue = "") String facebookId);

	@RequestMapping(value = "/user/createUser", method = {RequestMethod.POST})
	@ResponseStatus(value = HttpStatus.OK)
	CreateUserDTO createUser(@RequestBody CreateUserDTO createUserDTO);

	@RequestMapping(value = "/user/updateUser", method = {RequestMethod.POST})
	@ResponseStatus(value = HttpStatus.OK)
	CreateUserDTO updateUser(@RequestBody CreateUserDTO createUserDTO);
}