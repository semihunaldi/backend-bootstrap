
INSERT INTO T_ROLE(id,name) VALUES('f2ce1a80-b6c5-4b8c-a640-d8969958c385','ROLE_USER');
INSERT INTO T_ROLE(id,name) VALUES('c1e2d554-ffd2-4918-9a64-9a5c2e74d5e6','ROLE_ADMIN');

INSERT INTO t_user (id,deleted,create_user,create_userip,name,user_name,password,email,mobile_phone,facebook_id,twitter_id,instagram_id,fcm_token_id,age) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677','0','app-user','127.0.0.1','Name Surname','testuser','$2a$10$xn4D7VzCdkJd23URcQyrgupo/sEJEuwPEAyRA68n6AmHULXNCY3/e','test@test.com','905553332211','fbid','twid','insid','fcmtknid','26');
INSERT INTO t_user_roles(user_id, role_id) VALUES('9B18591A-C518-4CDE-BA3B-9ADC3145D677','f2ce1a80-b6c5-4b8c-a640-d8969958c385');

commit;