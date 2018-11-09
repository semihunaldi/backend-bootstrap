package com.semihunaldi.backendbootstrap.services.user.impl;

import com.semihunaldi.backendbootstrap.entitymodel.enums.SpecialExceptions;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.UserException;
import com.semihunaldi.backendbootstrap.entitymodel.mongo.TestDocument;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import com.semihunaldi.backendbootstrap.services.BaseServiceImpl;
import com.semihunaldi.backendbootstrap.services.dao.user.UserRepository;
import com.semihunaldi.backendbootstrap.services.mongo.TestRepository;
import com.semihunaldi.backendbootstrap.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Component(value = "userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestRepository testRepository;

	@Override
	public TestDocument testMongo(String userId) {
		return testRepository.findByTestString(userId);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User findUserById(String id) {
		return userRepository.findUserById(id);
	}

	@Override
	public User findUserByTwitterId(String twitterId) {
		return this.userRepository.findUserByTwitterId(twitterId);
	}

	@Override
	public User findUserByInstagramId(String instagramId) {
		return this.userRepository.findUserByInstagramId(instagramId);
	}

	@Override
	public User findUserByFacebookId(String facebookId) {
		return this.userRepository.findUserByFacebookId(facebookId);
	}

	@Override
	public User findUserByUserName(String userName) {
		return this.userRepository.findUserByUserName(userName);
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		try{
			return this.userRepository.save(user);
		} catch(DataIntegrityViolationException e){
			throw new UserException(SpecialExceptions.USER_EXISTS_EXCEPTION);
		}
	}
}
