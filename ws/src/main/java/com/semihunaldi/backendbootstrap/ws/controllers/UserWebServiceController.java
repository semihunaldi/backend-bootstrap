package com.semihunaldi.backendbootstrap.ws.controllers;

import com.google.common.base.Preconditions;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserNotFoundException;
import com.semihunaldi.backendbootstrap.entitymodel.mongo.TestDocument;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import com.semihunaldi.backendbootstrap.services.user.UserService;
import com.semihunaldi.backendbootstrap.ws.model.CreateUserDTO;
import com.semihunaldi.backendbootstrap.ws.model.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by semih on 17.8.2015.
 */
@RestController
public class UserWebServiceController extends BaseRestController implements UserWebService {

	@Autowired
	private UserService userService;

	@Override
	public TestDocument testMongo(String userId) {
		return userService.testMongo(userId);
	}

	public UserDTO queryUserByEmail(@RequestParam(value = "email", defaultValue = "") String email) {
		User user = userService.findUserByEmail(email);
		if(user != null){
			return UserDTO.create(user);
		} else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public UserDTO queryUserById(@RequestParam(value = "id", defaultValue = "") String id) {
		User user = userService.findUserById(id);
		if(user != null){
			return UserDTO.create(user);
		} else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public UserDTO queryUserByTwitterId(@RequestParam(value = "twitterId", defaultValue = "") String twitterId) {
		User user = userService.findUserByTwitterId(twitterId);
		if(user != null){
			return UserDTO.create(user);
		} else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public UserDTO queryUserByUserName(@RequestParam(value = "userName", defaultValue = "") String userName) {
		User user = userService.findUserByUserName(userName);
		if(user != null){
			return UserDTO.create(user);
		} else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public UserDTO queryUserByInstagramId(@RequestParam(value = "instagramId", defaultValue = "") String instagramId) {
		User user = userService.findUserByInstagramId(instagramId);
		if(user != null){
			return UserDTO.create(user);
		} else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public UserDTO queryUserByFacebookId(@RequestParam(value = "facebookId", defaultValue = "") String facebookId) {
		User user = userService.findUserByFacebookId(facebookId);
		if(user != null){
			return UserDTO.create(user);
		} else{
			throw new UserNotFoundException();
		}
	}

	@Override
	public CreateUserDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
		User user = createUserDTO.createUserEntity();
		user = userService.saveUser(user);
		return CreateUserDTO.createCreateUserDTO(user);
	}

	@Override
	public CreateUserDTO updateUser(@RequestBody CreateUserDTO createUserDTO) {
		Preconditions.checkNotNull(createUserDTO, "request can not be empty");
		Preconditions.checkArgument(StringUtils.isNotBlank(createUserDTO.getUserId()), "user id cannot be empty");
		User user = userService.findUserById(createUserDTO.getUserId());
		if(user == null){
			throw new UserNotFoundException();
		}
		CreateUserDTO.updateUserEntityFields(user, createUserDTO);
		user = userService.saveUser(user);
		return CreateUserDTO.createCreateUserDTO(user);
	}

	@Override
	public void deleteUser(String userId) {
		userService.deleteUser(userId);
	}
}
