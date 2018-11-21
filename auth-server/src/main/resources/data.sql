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