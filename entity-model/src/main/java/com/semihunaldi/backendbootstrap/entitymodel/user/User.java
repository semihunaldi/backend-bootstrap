package com.semihunaldi.backendbootstrap.entitymodel.user;

import com.semihunaldi.backendbootstrap.entitymodel.AbstractEntity;
import com.semihunaldi.backendbootstrap.entitymodel.enums.YesNo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
public class User extends AbstractEntity {

	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	private String mobilePhone;

	private String facebookId;

	private String twitterId;

	private String instagramId;

	private Integer age;

	private String location;

	private String timeZone;

	@Enumerated(EnumType.STRING)
	private YesNo isProtectedAccount;

	private Integer twitterFriendCount;
}
