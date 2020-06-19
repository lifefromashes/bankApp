INSERT INTO bank_user(id, username, password, authority, is_active) 
	VALUES(0, 'admin', 'admin', 'ADMIN', TRUE) 
	ON DUPLICATE KEY UPDATE authority = 'ADMIN';