
    create table t_role (
       id varchar(255) not null,
        name varchar(60),
        primary key (id)
    )

    create table t_user (
       id varchar(255) not null,
        create_time timestamp,
        create_user varchar(255),
        create_userip varchar(255),
        deleted varchar(255),
        update_time timestamp,
        update_user varchar(255),
        update_userip varchar(255),
        version int4,
        age int4,
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
    )

    create table t_user_roles (
       user_id varchar(255) not null,
        role_id varchar(255) not null,
        primary key (user_id, role_id)
    )

    alter table if exists t_role 
       add constraint UK_qrnrwc0xe8apqp9be60ru1948 unique (name)

    alter table if exists t_user 
       add constraint T_USER_TWITTER_ID_UNIQUE unique (twitter_id)

    alter table if exists t_user 
       add constraint T_USER_USER_NAME_UNIQUE unique (user_name)

    alter table if exists t_user 
       add constraint T_USER_FACEBOOK_ID_UNIQUE unique (facebook_id)

    alter table if exists t_user 
       add constraint T_USER_INSTAGRAM_ID_UNIQUE unique (instagram_id)

    alter table if exists t_user 
       add constraint T_USER_EMAIL_UNIQUE unique (email)

    alter table if exists t_user_roles 
       add constraint FK9tnch7fr4rhhn0tmflj751iy8 
       foreign key (role_id) 
       references t_role

    alter table if exists t_user_roles 
       add constraint FKpqntgokae5e703qb206xvfdk3 
       foreign key (user_id) 
       references t_user
INSERT INTO T_ROLE(id,name) VALUES(1,'ROLE_USER')
INSERT INTO T_ROLE(id,name) VALUES(2,'ROLE_ADMIN')
INSERT INTO t_user (id,deleted,create_user,create_userip,name,user_name,password,email,mobile_phone,facebook_id,twitter_id,instagram_id,fcm_token_id,age) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677','0','app-user','127.0.0.1','Name Surname','testuser','$2a$10$xn4D7VzCdkJd23URcQyrgupo/sEJEuwPEAyRA68n6AmHULXNCY3/e','test@test.com','905553332211','fbid','twid','insid','fcmtknid','26')
INSERT INTO t_user_roles(user_id, role_id) VALUES('9B18591A-C518-4CDE-BA3B-9ADC3145D677',1)
commit

    create table t_role (
       id varchar(255) not null,
        name varchar(60),
        primary key (id)
    )

    create table t_user (
       id varchar(255) not null,
        create_time timestamp,
        create_user varchar(255),
        create_userip varchar(255),
        deleted varchar(255),
        update_time timestamp,
        update_user varchar(255),
        update_userip varchar(255),
        version int4,
        age int4,
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
    )

    create table t_user_roles (
       user_id varchar(255) not null,
        role_id varchar(255) not null,
        primary key (user_id, role_id)
    )

    alter table if exists t_role 
       add constraint UK_qrnrwc0xe8apqp9be60ru1948 unique (name)

    alter table if exists t_user 
       add constraint T_USER_TWITTER_ID_UNIQUE unique (twitter_id)

    alter table if exists t_user 
       add constraint T_USER_USER_NAME_UNIQUE unique (user_name)

    alter table if exists t_user 
       add constraint T_USER_FACEBOOK_ID_UNIQUE unique (facebook_id)

    alter table if exists t_user 
       add constraint T_USER_INSTAGRAM_ID_UNIQUE unique (instagram_id)

    alter table if exists t_user 
       add constraint T_USER_EMAIL_UNIQUE unique (email)

    alter table if exists t_user_roles 
       add constraint FK9tnch7fr4rhhn0tmflj751iy8 
       foreign key (role_id) 
       references t_role

    alter table if exists t_user_roles 
       add constraint FKpqntgokae5e703qb206xvfdk3 
       foreign key (user_id) 
       references t_user
INSERT INTO T_ROLE(id,name) VALUES(1,'ROLE_USER')
INSERT INTO T_ROLE(id,name) VALUES(2,'ROLE_ADMIN')
INSERT INTO t_user (id,deleted,create_user,create_userip,name,user_name,password,email,mobile_phone,facebook_id,twitter_id,instagram_id,fcm_token_id,age) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677','0','app-user','127.0.0.1','Name Surname','testuser','$2a$10$xn4D7VzCdkJd23URcQyrgupo/sEJEuwPEAyRA68n6AmHULXNCY3/e','test@test.com','905553332211','fbid','twid','insid','fcmtknid','26')
INSERT INTO t_user_roles(user_id, role_id) VALUES('9B18591A-C518-4CDE-BA3B-9ADC3145D677',1)
commit
