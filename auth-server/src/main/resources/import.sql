
INSERT INTO T_ROLE(id,name) VALUES('f2ce1a80-b6c5-4b8c-a640-d8969958c385','ROLE_USER');
INSERT INTO T_ROLE(id,name) VALUES('c1e2d554-ffd2-4918-9a64-9a5c2e74d5e6','ROLE_ADMIN');

INSERT INTO t_user (ID, CREATE_TIME, CREATE_USER, CREATE_USERIP, DELETED, UPDATE_TIME, UPDATE_USER, UPDATE_USERIP, VERSION, BIRTH_DATE, EMAIL, FACEBOOK_ID, FCM_TOKEN_ID, INSTAGRAM_ID, MOBILE_PHONE, NAME, PASSWORD, TWITTER_ID, USER_NAME) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677', '2018-11-29 06:16:38.923000', 'SYSTEM', '127.0.0.1', '0', '2018-11-29 06:16:39.555000', 'SYSTEM', '127.0.0.1', 0, '2018-11-29 05:58:53.000000' 'test@test.com', 'fbid', 'fcmtknid', 'insid', '905553332211', 'Name Surname', '$2a$10$xn4D7VzCdkJd23URcQyrgupo/sEJEuwPEAyRA68n6AmHULXNCY3/e', 'twid', 'testuser');
INSERT INTO t_user_roles(user_id, role_id) VALUES('9B18591A-C518-4CDE-BA3B-9ADC3145D677','f2ce1a80-b6c5-4b8c-a640-d8969958c385');

commit;

