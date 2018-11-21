package com.semihunaldi.backendbootstrap.authserver.repository;

import com.semihunaldi.backendbootstrap.authserver.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by semihunaldi on 21.11.2018
 */
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

	Credentials findByUsername(String username);
}
