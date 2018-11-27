package com.semihunaldi.backendbootstrap.authserver.config.oauth2;

import com.semihunaldi.backendbootstrap.authserver.config.oauth2.model.Credentials;
import com.semihunaldi.backendbootstrap.authserver.config.oauth2.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by semihunaldi on 21.11.2018
 */
@Profile("oauth2")
public class CustomJdbcUserDetailsService implements UserDetailsService {

	@Autowired
	private CredentialsRepository credentialsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credentials credentials = credentialsRepository.findByUsername(username);

		if(credentials == null){
			throw new UsernameNotFoundException(String.format("User %s cannot be found", username));
		}

		return new User(credentials.getUsername(),
				credentials.getPassword(),
				credentials.isEnabled(),
				true,
				true,
				true,
				credentials.getAuthorities());
	}
}
