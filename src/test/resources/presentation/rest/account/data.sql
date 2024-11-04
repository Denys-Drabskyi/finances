INSERT INTO finances.user(username, email)
	VALUES ('test', 'test@mail.com');

INSERT INTO finances.account(user_id, account_name, currency)
	VALUES (1, 'test1', 'USD'), (1, 'test2', 'USD') ;