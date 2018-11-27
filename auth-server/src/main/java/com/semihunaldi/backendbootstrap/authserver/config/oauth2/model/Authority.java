package com.semihunaldi.backendbootstrap.authserver.config.oauth2.model;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by semihunaldi on 21.11.2018
 */
@Entity
@Data
@Profile("oauth2")
public class Authority implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 6526810033450468086L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String authority;
}
