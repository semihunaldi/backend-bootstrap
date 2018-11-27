package com.semihunaldi.backendbootstrap.authserver.config.oauth2.model;

import lombok.Data;
import org.springframework.context.annotation.Profile;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * Created by semihunaldi on 21.11.2018
 */
@Entity
@Table(name = "credentials")
@Data
@Profile("oauth2")
public class Credentials implements Serializable {

	private static final long serialVersionUID = 3734072263583197829L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Integer version;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Authority> authorities;

	private boolean enabled;
}
