package com.semihunaldi.backendbootstrap.services.user;

import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import com.semihunaldi.backendbootstrap.services.BaseService;

/**
 * Created by semihunaldi on 9.11.2018
 */
public interface UserService extends BaseService {

	User findUserByEmail(String email);

	User findUserById(String id);

	User findUserByTwitterId(String twitterId);

	User findUserByInstagramId(String instagramId);

	User findUserByFacebookId(String facebookId);

	User findUserByUserName(String userName);

	User saveUser(User user);
}
