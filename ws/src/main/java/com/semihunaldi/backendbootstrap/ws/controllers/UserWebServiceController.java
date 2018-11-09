package com.semihunaldi.backendbootstrap.ws.controllers;

import com.google.common.base.Preconditions;
import com.semihunaldi.backendbootstrap.entitymodel.enums.SpecialExceptions;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.UserException;
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
		try{
			User user = userService.findUserByEmail(email);
			if(user != null){
				return UserDTO.create(user);
			} else{
				throw new UserException(SpecialExceptions.USER_NOT_FOUND_EXCEPTION);
			}
		} catch(UserException e){
			logger.error("queryUserByFacebookId error", e);
			return new UserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("queryUserByFacebookId error", e);
			return new UserDTO(SpecialExceptions.ERROR);
		}
	}

	@Override
	public UserDTO queryUserById(@RequestParam(value = "id", defaultValue = "") String id) {
		try{
			User user = userService.findUserById(id);
			if(user != null){
				return UserDTO.create(user);
			} else{
				throw new UserException(SpecialExceptions.USER_NOT_FOUND_EXCEPTION);
			}
		} catch(UserException e){
			logger.error("queryUserById error", e);
			return new UserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("queryUserById error", e);
			return new UserDTO(SpecialExceptions.ERROR);
		}
	}

	@Override
	public UserDTO queryUserByTwitterId(@RequestParam(value = "twitterId", defaultValue = "") String twitterId) {
		try{
			User user = userService.findUserByTwitterId(twitterId);
			if(user != null){
				return UserDTO.create(user);
			} else{
				throw new UserException(SpecialExceptions.USER_NOT_FOUND_EXCEPTION);
			}
		} catch(UserException e){
			logger.error("queryUserByTwitterId error", e);
			return new UserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("queryUserByTwitterId error", e);
			return new UserDTO(SpecialExceptions.ERROR);
		}
	}

	@Override
	public UserDTO queryUserByUserName(@RequestParam(value = "userName", defaultValue = "") String userName) {
		try{
			User user = userService.findUserByUserName(userName);
			if(user != null){
				return UserDTO.create(user);
			} else{
				throw new UserException(SpecialExceptions.USER_NOT_FOUND_EXCEPTION);
			}
		} catch(UserException e){
			logger.error("queryUserByUserName error", e);
			return new UserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("queryUserByUserName error", e);
			return new UserDTO(SpecialExceptions.ERROR);
		}
	}

	@Override
	public UserDTO queryUserByInstagramId(@RequestParam(value = "instagramId", defaultValue = "") String instagramId) {
		try{
			User user = userService.findUserByInstagramId(instagramId);
			if(user != null){
				return UserDTO.create(user);
			} else{
				throw new UserException(SpecialExceptions.USER_NOT_FOUND_EXCEPTION);
			}
		} catch(UserException e){
			logger.error("queryUserByInstagramId error", e);
			return new UserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("queryUserByInstagramId error", e);
			return new UserDTO(SpecialExceptions.ERROR);
		}
	}

	@Override
	public UserDTO queryUserByFacebookId(@RequestParam(value = "facebookId", defaultValue = "") String facebookId) {
		try{
			User user = userService.findUserByFacebookId(facebookId);
			if(user != null){
				return UserDTO.create(user);
			} else{
				throw new UserException(SpecialExceptions.USER_NOT_FOUND_EXCEPTION);
			}
		} catch(UserException e){
			logger.error("queryUserByFacebookId error", e);
			return new UserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("queryUserByFacebookId error", e);
			return new UserDTO(SpecialExceptions.ERROR);
		}
	}

	@Override
	public CreateUserDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
		try{
			User user = createUserDTO.createUserEntity();
			user = userService.saveUser(user);
			return CreateUserDTO.createCreateUserDTO(user);
		} catch(UserException e){
			logger.error("createUser error", e);
			return new CreateUserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("createUser error", e);
			return new CreateUserDTO(SpecialExceptions.ERROR);
		}
	}

	@Override
	public CreateUserDTO updateUser(@RequestBody CreateUserDTO createUserDTO) {
		try{
			Preconditions.checkNotNull(createUserDTO, "request can not be empty");
			Preconditions.checkArgument(StringUtils.isNotBlank(createUserDTO.getUserId()), "user id cannot be empty");
			User user = userService.findUserById(createUserDTO.getUserId());
			if(user == null){
				throw new UserException(SpecialExceptions.USER_NOT_FOUND_EXCEPTION);
			}
			CreateUserDTO.updateUserEntityFields(user, createUserDTO);
			user = userService.saveUser(user);
			return CreateUserDTO.createCreateUserDTO(user);
		} catch(UserException e){
			logger.error("updateUser error", e);
			return new CreateUserDTO(e.getErrorCode(), e.getErrorDescription());
		} catch(Exception e){
			logger.error("updateUser error", e);
			return new CreateUserDTO(SpecialExceptions.ERROR);
		}
	}
}
