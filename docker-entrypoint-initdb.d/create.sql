create sequence hibernate_sequence start with 2 increment by 1

drop table if exists t_role;
create table t_role (
  id varchar(255) not null,
  name varchar(60),
  primary key (id)
);

drop table if exists t_user;
create table t_user (
  id varchar(255) not null,
  create_time timestamp,
  create_user varchar(255),
  create_userip varchar(255),
  deleted varchar(255),
  update_time timestamp default CURRENT_TIMESTAMP,
  update_user varchar(255),
  update_userip varchar(255),
  version integer,
  age integer,
  email varchar(255),
  facebook_id varchar(255),
  fcm_token_id varchar(255),
  instagram_id varchar(255),
  mobile_phone varchar(255),
  name varchar(255),
  password varchar(255),
  twitter_id varchar(255),
  user_name varchar(255),
  primary key (id)
);

alter table t_user
  add constraint T_USER_TWITTER_ID_UNIQUE unique (twitter_id);

alter table t_user
  add constraint T_USER_USER_NAME_UNIQUE unique (user_name);

alter table t_user
  add constraint T_USER_FACEBOOK_ID_UNIQUE unique (facebook_id);

alter table t_user
  add constraint T_USER_INSTAGRAM_ID_UNIQUE unique (instagram_id);

alter table t_user
  add constraint T_USER_EMAIL_UNIQUE unique (email);


drop table if exists t_user_roles;
create table t_user_roles (
  user_id varchar(255) not null,
  role_id varchar(255) not null,
  primary key (user_id, role_id)
);

alter table t_user_roles
  add constraint FKlbohd47sbyyx5eqbjgwi4sjky
foreign key (role_id)
references t_role;

alter table t_user_roles
  add constraint FKn3jfyu68eps3hj1bgy577lrfs
foreign key (user_id)
references t_user;


-- AUTH-SERVER
drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
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
  access_token_validity integer DEFAULT NULL,
  refresh_token_validity integer DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);
drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);
drop table if exists oauth_refresh_token;
create table oauth_refresh_token(
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
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
  code VARCHAR(255), authentication BYTEA
);
drop table if exists oauth_approvals;
create table oauth_approvals (
  userId VARCHAR(255),
  clientId VARCHAR(255),
  scope VARCHAR(255),
  status VARCHAR(10),
  expiresAt DATE,
  lastModifiedAt DATE
);


-- QUARTZ

drop table if exists qrtz_job_details CASCADE;
create table qrtz_job_details
(
  sched_name varchar(120) not null,
  job_name varchar(200) not null,
  job_group varchar(200) not null,
  description varchar(250),
  job_class_name varchar(250) not null,
  is_durable boolean not null,
  is_nonconcurrent boolean not null,
  is_update_data boolean not null,
  requests_recovery boolean not null,
  job_data bytea,
  constraint qrtz_job_details_pkey
  primary key (sched_name, job_name, job_group)
)
;

create index idx_qrtz_j_req_recovery
  on qrtz_job_details (sched_name, requests_recovery)
;

create index idx_qrtz_j_grp
  on qrtz_job_details (sched_name, job_group)
;

drop table if exists qrtz_triggers CASCADE;
create table qrtz_triggers
(
  sched_name varchar(120) not null,
  trigger_name varchar(200) not null,
  trigger_group varchar(200) not null,
  job_name varchar(200) not null,
  job_group varchar(200) not null,
  description varchar(250),
  next_fire_time bigint,
  prev_fire_time bigint,
  priority integer,
  trigger_state varchar(16) not null,
  trigger_type varchar(8) not null,
  start_time bigint not null,
  end_time bigint,
  calendar_name varchar(200),
  misfire_instr smallint,
  job_data bytea,
  constraint qrtz_triggers_pkey
  primary key (sched_name, trigger_name, trigger_group),
  constraint qrtz_triggers_sched_name_fkey
  foreign key (sched_name, job_name, job_group) references qrtz_job_details
)
;

create index idx_qrtz_t_j
  on qrtz_triggers (sched_name, job_name, job_group)
;

create index idx_qrtz_t_jg
  on qrtz_triggers (sched_name, job_group)
;

create index idx_qrtz_t_c
  on qrtz_triggers (sched_name, calendar_name)
;

create index idx_qrtz_t_g
  on qrtz_triggers (sched_name, trigger_group)
;

create index idx_qrtz_t_state
  on qrtz_triggers (sched_name, trigger_state)
;

create index idx_qrtz_t_n_state
  on qrtz_triggers (sched_name, trigger_name, trigger_group, trigger_state)
;

create index idx_qrtz_t_n_g_state
  on qrtz_triggers (sched_name, trigger_group, trigger_state)
;

create index idx_qrtz_t_next_fire_time
  on qrtz_triggers (sched_name, next_fire_time)
;

create index idx_qrtz_t_nft_st
  on qrtz_triggers (sched_name, trigger_state, next_fire_time)
;

create index idx_qrtz_t_nft_misfire
  on qrtz_triggers (sched_name, misfire_instr, next_fire_time)
;

create index idx_qrtz_t_nft_st_misfire
  on qrtz_triggers (sched_name, misfire_instr, next_fire_time, trigger_state)
;

create index idx_qrtz_t_nft_st_misfire_grp
  on qrtz_triggers (sched_name, misfire_instr, next_fire_time, trigger_group, trigger_state)
;

drop table if exists qrtz_simple_triggers CASCADE;
create table qrtz_simple_triggers
(
  sched_name varchar(120) not null,
  trigger_name varchar(200) not null,
  trigger_group varchar(200) not null,
  repeat_count bigint not null,
  repeat_interval bigint not null,
  times_triggered bigint not null,
  constraint qrtz_simple_triggers_pkey
  primary key (sched_name, trigger_name, trigger_group),
  constraint qrtz_simple_triggers_sched_name_fkey
  foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers
)
;

drop table if exists qrtz_cron_triggers CASCADE;
create table qrtz_cron_triggers
(
  sched_name varchar(120) not null,
  trigger_name varchar(200) not null,
  trigger_group varchar(200) not null,
  cron_expression varchar(120) not null,
  time_zone_id varchar(80),
  constraint qrtz_cron_triggers_pkey
  primary key (sched_name, trigger_name, trigger_group),
  constraint qrtz_cron_triggers_sched_name_fkey
  foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers
)
;

drop table if exists qrtz_simprop_triggers CASCADE;
create table qrtz_simprop_triggers
(
  sched_name varchar(120) not null,
  trigger_name varchar(200) not null,
  trigger_group varchar(200) not null,
  str_prop_1 varchar(512),
  str_prop_2 varchar(512),
  str_prop_3 varchar(512),
  int_prop_1 integer,
  int_prop_2 integer,
  long_prop_1 bigint,
  long_prop_2 bigint,
  dec_prop_1 numeric(13,4),
  dec_prop_2 numeric(13,4),
  bool_prop_1 boolean,
  bool_prop_2 boolean,
  constraint qrtz_simprop_triggers_pkey
  primary key (sched_name, trigger_name, trigger_group),
  constraint qrtz_simprop_triggers_sched_name_fkey
  foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers
)
;

drop table if exists qrtz_blob_triggers CASCADE;
create table qrtz_blob_triggers
(
  sched_name varchar(120) not null,
  trigger_name varchar(200) not null,
  trigger_group varchar(200) not null,
  blob_data bytea,
  constraint qrtz_blob_triggers_pkey
  primary key (sched_name, trigger_name, trigger_group),
  constraint qrtz_blob_triggers_sched_name_fkey
  foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers
)
;

drop table if exists qrtz_calendars CASCADE;
create table qrtz_calendars
(
  sched_name varchar(120) not null,
  calendar_name varchar(200) not null,
  calendar bytea not null,
  constraint qrtz_calendars_pkey
  primary key (sched_name, calendar_name)
)
;

drop table if exists qrtz_paused_trigger_grps CASCADE;
create table qrtz_paused_trigger_grps
(
  sched_name varchar(120) not null,
  trigger_group varchar(200) not null,
  constraint qrtz_paused_trigger_grps_pkey
  primary key (sched_name, trigger_group)
)
;

drop table if exists qrtz_fired_triggers CASCADE;
create table qrtz_fired_triggers
(
  sched_name varchar(120) not null,
  entry_id varchar(95) not null,
  trigger_name varchar(200) not null,
  trigger_group varchar(200) not null,
  instance_name varchar(200) not null,
  fired_time bigint not null,
  sched_time bigint not null,
  priority integer not null,
  state varchar(16) not null,
  job_name varchar(200),
  job_group varchar(200),
  is_nonconcurrent boolean,
  requests_recovery boolean,
  constraint qrtz_fired_triggers_pkey
  primary key (sched_name, entry_id)
)
;

create index idx_qrtz_ft_trig_inst_name
  on qrtz_fired_triggers (sched_name, instance_name)
;

create index idx_qrtz_ft_inst_job_req_rcvry
  on qrtz_fired_triggers (sched_name, instance_name, requests_recovery)
;

create index idx_qrtz_ft_j_g
  on qrtz_fired_triggers (sched_name, job_name, job_group)
;

create index idx_qrtz_ft_jg
  on qrtz_fired_triggers (sched_name, job_group)
;

create index idx_qrtz_ft_t_g
  on qrtz_fired_triggers (sched_name, trigger_name, trigger_group)
;

create index idx_qrtz_ft_tg
  on qrtz_fired_triggers (sched_name, trigger_group)
;

drop table if exists qrtz_scheduler_state CASCADE;
create table qrtz_scheduler_state
(
  sched_name varchar(120) not null,
  instance_name varchar(200) not null,
  last_checkin_time bigint not null,
  checkin_interval bigint not null,
  constraint qrtz_scheduler_state_pkey
  primary key (sched_name, instance_name)
)
;

drop table if exists qrtz_locks CASCADE;
create table qrtz_locks
(
  sched_name varchar(120) not null,
  lock_name varchar(40) not null,
  constraint qrtz_locks_pkey
  primary key (sched_name, lock_name)
)
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

INSERT INTO T_ROLE(id,name) VALUES('f2ce1a80-b6c5-4b8c-a640-d8969958c385','ROLE_USER');
INSERT INTO T_ROLE(id,name) VALUES('c1e2d554-ffd2-4918-9a64-9a5c2e74d5e6','ROLE_ADMIN');

INSERT INTO t_user (id,deleted,create_user,create_userip,name,user_name,password,email,mobile_phone,facebook_id,twitter_id,instagram_id,fcm_token_id,age) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677','0','app-user','127.0.0.1','Name Surname','testuser','$2a$10$xn4D7VzCdkJd23URcQyrgupo/sEJEuwPEAyRA68n6AmHULXNCY3/e','test@test.com','905553332211','fbid','twid','insid','fcmtknid','26');
INSERT INTO t_user_roles(user_id, role_id) VALUES('9B18591A-C518-4CDE-BA3B-9ADC3145D677','f2ce1a80-b6c5-4b8c-a640-d8969958c385');

commit;
