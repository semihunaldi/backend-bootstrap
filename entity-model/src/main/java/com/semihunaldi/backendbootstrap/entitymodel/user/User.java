package com.semihunaldi.backendbootstrap.entitymodel.user;

import com.google.common.collect.Sets;
import com.semihunaldi.backendbootstrap.entitymodel.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Entity
@Data
@Table(name = "T_USER", uniqueConstraints = {@UniqueConstraint(name = "T_USER_TWITTER_ID_UNIQUE", columnNames = "twitterId"),
		@UniqueConstraint(name = "T_USER_USER_NAME_UNIQUE", columnNames = "userName"),
		@UniqueConstraint(name = "T_USER_FACEBOOK_ID_UNIQUE", columnNames = "facebookId"),
		@UniqueConstraint(name = "T_USER_INSTAGRAM_ID_UNIQUE", columnNames = "instagramId"),
		@UniqueConstraint(name = "T_USER_EMAIL_UNIQUE", columnNames = "email")})
@DynamicUpdate
@EqualsAndHashCode(callSuper = true, of = "")
@Where(clause = "DELETED = '0'")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User extends AbstractEntity {

	@NotBlank
	@Size(min = 4, max = 40)
	private String name;

	@NotBlank
	@Size(min = 3, max = 15)
	private String userName;

	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	private String mobilePhone;

	private String facebookId;

	private String twitterId;

	private String instagramId;

	private String fcmTokenId;

	@NotNull
	private Date birthDate;

	@NotBlank
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_user_roles",
	           joinColumns = @JoinColumn(name = "user_id"),
	           inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = Sets.newHashSet();

	public User(String name, String username, String email, String password) {
		this.name = name;
		this.userName = username;
		this.email = email;
		this.password = password;
	}
}
