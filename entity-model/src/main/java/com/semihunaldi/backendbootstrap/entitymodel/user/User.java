package com.semihunaldi.backendbootstrap.entitymodel.user;

import com.google.common.collect.Sets;
import com.semihunaldi.backendbootstrap.entitymodel.AbstractEntity;
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
public class User extends AbstractEntity {

	private String name;

	private String userName;

	private String email;

	private String mobilePhone;

	private String facebookId;

	private String twitterId;

	private String instagramId;

	private String fcmTokenId;

	private Integer age;

	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
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
