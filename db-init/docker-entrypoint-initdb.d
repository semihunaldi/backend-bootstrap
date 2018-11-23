-- noinspection SqlNoDataSourceInspectionForFile

drop table if exists t_user
create table t_user (
   id varchar(255) not null,
    create_time timestamp,
    create_user varchar(255),
    create_userip varchar(255),
    deleted varchar(255),
    update_time timestamp,
    update_user varchar(255),
    update_userip varchar(255),
    version integer,
    age integer,
    email varchar(255),
    facebook_id varchar(255),
    fcm_token_id varchar(255),
    first_name varchar(255),
    instagram_id varchar(255),
    last_name varchar(255),
    mobile_phone varchar(255),
    twitter_id varchar(255),
    user_name varchar(255),
    primary key (id)
)

alter table t_user
   add constraint T_USER_TWITTER_ID_UNIQUE unique (twitter_id)

alter table t_user
   add constraint T_USER_USER_NAME_UNIQUE unique (user_name)

alter table t_user
   add constraint T_USER_FACEBOOK_ID_UNIQUE unique (facebook_id)

alter table t_user
   add constraint T_USER_INSTAGRAM_ID_UNIQUE unique (instagram_id)

alter table t_user
   add constraint T_USER_EMAIL_UNIQUE unique (email)



drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
drop table if exists oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity integer(11) DEFAULT NULL,
  refresh_token_validity integer(11) DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);
drop table if exists oauth_access_token;
create table `oauth_access_token` (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGBLOB,
  refresh_token VARCHAR(255)
);
drop table if exists oauth_refresh_token;
create table `oauth_refresh_token`(
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication LONGBLOB
);
drop table if exists authority;
CREATE TABLE authority (
  id  integer,
  authority varchar(255),
  primary key (id)
);
drop table if exists credentials;

CREATE TABLE credentials (
  id  integer,
  enabled boolean not null,
  username varchar(255) not null,
  password varchar(255) not null,
  version integer,
  primary key (id)
);
drop table if exists credentials_authorities;
CREATE TABLE credentials_authorities (
  credentials_id bigint not null,
  authorities_id bigint not null
);
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication LONGBLOB
);
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);


--QUARTZ

drop table if exists qrtz_blob_triggers
create table qrtz_blob_triggers
(
	sched_name VARCHAR(120) not null,
	trigger_name VARCHAR(200) not null,
	trigger_group VARCHAR(200) not null,
	blob_data BLOB
)
;

drop table if exists qrtz_calendars
create table qrtz_calendars
(
	sched_name VARCHAR(120) not null,
	calendar_name VARCHAR(200) not null,
	calendar BLOB not null,
	constraint pk_qrtz_calendars
		primary key (sched_name, calendar_name)
)
;

drop table if exists qrtz_fired_triggers
create table qrtz_fired_triggers
(
	sched_name VARCHAR(120) not null,
	entry_id VARCHAR(95) not null,
	trigger_name VARCHAR(200) not null,
	trigger_group VARCHAR(200) not null,
	instance_name VARCHAR(200) not null,
	fired_time BIGINT not null,
	sched_time BIGINT not null,
	priority INTEGER not null,
	state VARCHAR(16) not null,
	job_name VARCHAR(200),
	job_group VARCHAR(200),
	is_nonconcurrent BOOLEAN,
	requests_recovery BOOLEAN,
	constraint pk_qrtz_fired_triggers
		primary key (sched_name, entry_id)
)
;

drop table if exists qrtz_job_details
create table qrtz_job_details
(
	sched_name VARCHAR(120) not null,
	job_name VARCHAR(200) not null,
	job_group VARCHAR(200) not null,
	description VARCHAR(250),
	job_class_name VARCHAR(250) not null,
	is_durable BOOLEAN not null,
	is_nonconcurrent BOOLEAN not null,
	is_update_data BOOLEAN not null,
	requests_recovery BOOLEAN not null,
	job_data BLOB,
	constraint pk_qrtz_job_details
		primary key (sched_name, job_name, job_group)
)
;

drop table if exists qrtz_locks
create table qrtz_locks
(
	sched_name VARCHAR(120) not null,
	lock_name VARCHAR(40) not null,
	constraint pk_qrtz_locks
		primary key (sched_name, lock_name)
)
;

drop table if exists qrtz_paused_trigger_grps
create table qrtz_paused_trigger_grps
(
	sched_name VARCHAR(120) not null,
	trigger_group VARCHAR(200) not null,
	constraint pk_qrtz_paused_trigger_grps
		primary key (sched_name, trigger_group)
)
;

drop table if exists qrtz_scheduler_state
create table qrtz_scheduler_state
(
	sched_name VARCHAR(120) not null,
	instance_name VARCHAR(200) not null,
	last_checkin_time BIGINT not null,
	checkin_interval BIGINT not null,
	constraint pk_qrtz_scheduler_state
		primary key (sched_name, instance_name)
)
;

drop table if exists qrtz_triggers
create table qrtz_triggers
(
	sched_name VARCHAR(120) not null,
	trigger_name VARCHAR(200) not null,
	trigger_group VARCHAR(200) not null,
	job_name VARCHAR(200) not null,
	job_group VARCHAR(200) not null
		constraint fk_qrtz_triggers_qrtz_job_details
			references qrtz_job_details (job_group),
	description VARCHAR(250),
	next_fire_time BIGINT,
	prev_fire_time BIGINT,
	priority INTEGER,
	trigger_state VARCHAR(16) not null,
	trigger_type VARCHAR(8) not null,
	start_time BIGINT not null,
	end_time BIGINT,
	calendar_name VARCHAR(200),
	misfire_instr SMALLINT,
	job_data BLOB,
	constraint pk_qrtz_triggers
		primary key (sched_name, trigger_name, trigger_group)
)
;

drop table if exists qrtz_cron_triggers
create table qrtz_cron_triggers
(
	sched_name VARCHAR(120) not null,
	trigger_name VARCHAR(200) not null,
	trigger_group VARCHAR(200) not null
		constraint fk_qrtz_cron_triggers_qrtz_triggers
			references qrtz_triggers (trigger_group)
				on delete cascade,
	cron_expression VARCHAR(120) not null,
	time_zone_id VARCHAR(80),
	constraint pk_qrtz_cron_triggers
		primary key (sched_name, trigger_name, trigger_group)
)
;

drop table if exists qrtz_simple_triggers
create table qrtz_simple_triggers
(
	sched_name VARCHAR(120) not null,
	trigger_name VARCHAR(200) not null,
	trigger_group VARCHAR(200) not null
		constraint fk_qrtz_simple_triggers_qrtz_triggers
			references qrtz_triggers (trigger_group)
				on delete cascade,
	repeat_count BIGINT not null,
	repeat_interval BIGINT not null,
	times_triggered BIGINT not null,
	constraint pk_qrtz_simple_triggers
		primary key (sched_name, trigger_name, trigger_group)
)
;

drop table if exists qrtz_simprop_triggers
create table qrtz_simprop_triggers
(
	sched_name VARCHAR(120) not null,
	trigger_name VARCHAR(200) not null,
	trigger_group VARCHAR(200) not null
		constraint fk_qrtz_simprop_triggers_qrtz_triggers
			references qrtz_triggers (trigger_group)
				on delete cascade,
	str_prop_1 VARCHAR(512),
	str_prop_2 VARCHAR(512),
	str_prop_3 VARCHAR(512),
	int_prop_1 INTEGER,
	int_prop_2 INTEGER,
	long_prop_1 BIGINT,
	long_prop_2 BIGINT,
	dec_prop_1 DECIMAL(13,4),
	dec_prop_2 DECIMAL(13,4),
	bool_prop_1 BOOLEAN,
	bool_prop_2 BOOLEAN,
	constraint pk_qrtz_simprop_triggers
		primary key (sched_name, trigger_name, trigger_group)
)
;

create index fk_qrtz_triggers_qrtz_job_details_index_3
	on qrtz_triggers (sched_name, job_name, job_group)
;

INSERT INTO authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_API_ADMIN');

INSERT INTO credentials VALUES(1,true,'oauth_admin','$2a$10$TXYbtrgn5/6hO9xpd/bkPuUJ9abOxXzWrbRzlse0djMUO51vJ2h1i','0');
INSERT INTO credentials VALUES(2,true,'resource_admin','$2a$10$TXYbtrgn5/6hO9xpd/bkPuUJ9abOxXzWrbRzlse0djMUO51vJ2h1i','0');
INSERT INTO credentials  VALUES(3,true,'regular_user','$2a$10$TXYbtrgn5/6hO9xpd/bkPuUJ9abOxXzWrbRzlse0djMUO51vJ2h1i','0');
--$2a$10$TXYbtrgn5/6hO9xpd/bkPuUJ9abOxXzWrbRzlse0djMUO51vJ2h1i  = password

INSERT INTO credentials_authorities VALUES (1,1);
INSERT INTO credentials_authorities VALUES (2,2);
INSERT INTO credentials_authorities VALUES (3,3);

INSERT INTO oauth_client_details VALUES('curl_client','backend-bootstrap-api', '$2a$10$TXYbtrgn5/6hO9xpd/bkPuUJ9abOxXzWrbRzlse0djMUO51vJ2h1i', 'read,write', 'client_credentials,authorization_code,refresh_token,password', 'http://127.0.0.1:9091/callback', 'ROLE_API_ADMIN', 7200, 1200, NULL, 'true');


INSERT INTO t_user (id,deleted,create_user,create_userip,,first_name,last_name,user_name,email,mobile_phone,facebook_id,twitter_id,instagram_id,fcm_token_id,age) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677','0','app-user','127.0.0.1','Name','Surname','testuser','test@test.com','905553332211','fbid','twid',insid,'fcmtknid','26');
commit;




