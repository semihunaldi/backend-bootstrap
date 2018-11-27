package com.semihunaldi.backendbootstrap.authserver.config.oauth2.repository;

import com.semihunaldi.backendbootstrap.authserver.config.oauth2.model.Credentials;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by semihunaldi on 21.11.2018
 */

@Repository
@Profile("oauth2")
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

	Credentials findByUsername(String username);
}
