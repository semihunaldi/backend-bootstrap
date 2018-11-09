package com.semihunaldi.backendbootstrap.services.dao.user;

import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("select user from User user where user.deleted='0'")
	List<User> findAllUsers();

	@Query("select user from User user where user.email=:email and user.deleted='0'")
	User findUserByEmail(@Param("email") String email);

	@Query("select user from User user where user.id=:id and user.deleted='0'")
	User findUserById(@Param("id") String id);

	@Query("select user from User user where user.twitterId=:twitterId and user.deleted='0'")
	User findUserByTwitterId(@Param("twitterId") String twitterId);

	@Query("select user from User user where user.userName=:userName and user.deleted='0'")
	User findUserByUserName(@Param("userName") String userName);

	@Query("select user from User user where user.facebookId=:facebookId and user.deleted='0'")
	User findUserByFacebookId(@Param("facebookId") String facebookId);

	@Query("select user from User user where user.instagramId=:instagramId and user.deleted='0'")
	User findUserByInstagramId(@Param("instagramId") String instagramId);
}
