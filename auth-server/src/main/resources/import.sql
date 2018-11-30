
INSERT INTO T_ROLE(id,name) VALUES('f2ce1a80-b6c5-4b8c-a640-d8969958c385','ROLE_USER');
INSERT INTO T_ROLE(id,name) VALUES('c1e2d554-ffd2-4918-9a64-9a5c2e74d5e6','ROLE_ADMIN');

INSERT INTO t_user (id, create_time, create_user, create_userip, deleted, update_time, update_user, update_userip, version, birth_date, email, facebook_id, fcm_token_id, instagram_id, mobile_phone, name, password, twitter_id, user_name) VALUES ('9B18591A-C518-4CDE-BA3B-9ADC3145D677', now(), 'SYSTEM', '127.0.0.1', '0', now(), 'SYSTEM', '127.0.0.1', 0, now(), 'test@test.com', 'fbid', 'fcmtknid', 'insid', '905553332211', 'Name Surname', '$2a$10$xn4D7VzCdkJd23URcQyrgupo/sEJEuwPEAyRA68n6AmHULXNCY3/e', 'twid', 'testuser');
INSERT INTO t_user_roles(user_id, role_id) VALUES('9B18591A-C518-4CDE-BA3B-9ADC3145D677','f2ce1a80-b6c5-4b8c-a640-d8969958c385');

commit;

