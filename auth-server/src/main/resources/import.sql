INSERT INTO T_ROLE(id,name) VALUES(1,'ROLE_USER');
INSERT INTO T_ROLE(id,name) VALUES(2,'ROLE_ADMIN');

INSERT INTO t_user (id,deleted,create_user,create_userip,name,user_name,password,email,mobile_phone,facebook_id,twitter_id,instagram_id,fcm_token_id,age) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677','0','app-user','127.0.0.1','Name Surname','testuser','$2a$10$xn4D7VzCdkJd23URcQyrgupo/sEJEuwPEAyRA68n6AmHULXNCY3/e','test@test.com','905553332211','fbid','twid','insid','fcmtknid','26');
INSERT INTO t_user_roles(user_id, role_id) VALUES('9B18591A-C518-4CDE-BA3B-9ADC3145D677',1);

commit;