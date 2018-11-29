package com.semihunaldi.backendbootstrap.authserver.config.jwt.security;

import com.semihunaldi.backendbootstrap.entitymodel.exceptions.user.UserNotFoundException;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Profile("jwt")
public class CustomJWTUserDetailsService implements UserDetailsService {

	@Autowired
	private UserJWTRepository userJWTRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail)
			throws UsernameNotFoundException {
		// Let people login with either username or email
		User user = userJWTRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() ->
						new UserNotFoundException("User not found with userName or email : " + usernameOrEmail)
				);

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(String id) {
		User user = userJWTRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id " + id)
		);

		return UserPrincipal.create(user);
	}
}