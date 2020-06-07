INSERT INTO user(id, username, password, authority) 
	VALUES(0, 'admin', 'admin', 'ADMIN') 
	ON DUPLICATE KEY UPDATE authority = 'ADMIN';