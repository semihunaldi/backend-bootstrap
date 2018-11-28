package com.semihunaldi.backendbootstrap.ws.controllers;

import com.semihunaldi.backendbootstrap.entitymodel.mongo.TestDocument;
import com.semihunaldi.backendbootstrap.ws.model.CreateUserDTO;
import com.semihunaldi.backendbootstrap.ws.model.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by semihunaldi on 9.11.2018
 */

@RequestMapping(path = "/api/user")
@Api(value = "User Web Service", description = "This API provides actions to users",
     basePath = "/api/user", produces = "application/json")
public interface UserWebService {

	@RequestMapping(value = "/testMongo", method = {RequestMethod.GET})
	TestDocument testMongo(@RequestParam(value = "userId", defaultValue = "") String userId);

	@RequestMapping(value = "/queryUserByEmail", method = {RequestMethod.GET})
	UserDTO queryUserByEmail(@RequestParam(value = "email", defaultValue = "") String email);

	@RequestMapping(value = "/queryUserById", method = {RequestMethod.GET})
	UserDTO queryUserById(@RequestParam(value = "id", defaultValue = "") String id);

	@RequestMapping(value = "/queryUserByTwitterId", method = {RequestMethod.GET})
	UserDTO queryUserByTwitterId(@RequestParam(value = "twitterId", defaultValue = "") String twitterId);

	@RequestMapping(value = "/queryUserByUserName", method = {RequestMethod.GET})
	UserDTO queryUserByUserName(@RequestParam(value = "userName", defaultValue = "") String userName);

	@RequestMapping(value = "/queryUserByInstagramId", method = {RequestMethod.GET})
	UserDTO queryUserByInstagramId(@RequestParam(value = "instagramId", defaultValue = "") String instagramId);

	@RequestMapping(value = "/queryUserByFacebookId", method = {RequestMethod.GET})
	UserDTO queryUserByFacebookId(@RequestParam(value = "facebookId", defaultValue = "") String facebookId);

	@RequestMapping(value = "/createUser", method = {RequestMethod.POST})
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Create a User with the given request object", response = UserDTO.class)
	CreateUserDTO createUser(@RequestBody CreateUserDTO createUserDTO);

	@RequestMapping(value = "/updateUser", method = {RequestMethod.POST})
	@ResponseStatus(value = HttpStatus.OK)
	CreateUserDTO updateUser(@RequestBody CreateUserDTO createUserDTO);

	@RequestMapping(value = "/deleteUser", method = {RequestMethod.DELETE})
	@ResponseStatus(value = HttpStatus.OK)
	void deleteUser(@RequestParam String userId);
}
