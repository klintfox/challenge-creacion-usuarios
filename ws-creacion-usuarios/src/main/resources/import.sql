-- INSERT INTO blacklist (ip) VALUES('186.84.91.59');
INSERT INTO user (id, name, email, password, created, modified, last_login, is_active) VALUES (1, 'klint','klint@hotmail.cl','','20-15-2020','19-08-2020','20-08-2020','yes');


INSERT INTO user_phones (id, number, city_code, country_code, fk_user) VALUES (1, '987654321', '10','57', 1);