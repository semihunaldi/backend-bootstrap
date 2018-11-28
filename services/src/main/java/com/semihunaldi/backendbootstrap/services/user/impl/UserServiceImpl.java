package com.semihunaldi.backendbootstrap.services.user.impl;

import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserExistsException;
import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserNotFoundException;
import com.semihunaldi.backendbootstrap.entitymodel.mongo.TestDocument;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import com.semihunaldi.backendbootstrap.services.BaseServiceImpl;
import com.semihunaldi.backendbootstrap.services.aspect.DisableLogging;
import com.semihunaldi.backendbootstrap.services.dao.user.UserRepository;
import com.semihunaldi.backendbootstrap.services.mongo.TestRepository;
import com.semihunaldi.backendbootstrap.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
	@DisableLogging
	public TestDocument testMongo(String userId) {
		TestDocument byTestString = testRepository.findByUserId(userId);
		if(byTestString == null){
			TestDocument testDocument = new TestDocument();
			testDocument.setUserId(userId);
			testDocument.setTimestamp(new Date());
			testDocument.setTestString("testString");
			return testRepository.save(testDocument);
		}
		return byTestString;
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
			return this.userRepository.saveAndFlush(user);
		} catch(DataIntegrityViolationException e){
			throw new UserExistsException(e);
		}
	}

	@Override
	@Transactional
	public void deleteUser(String userId) {
		User user = this.userRepository.findUserById(userId);
		if(user == null){
			throw new UserNotFoundException();
		}
		adjustEntityForDeletion(user);
		this.userRepository.save(user);
	}
}
