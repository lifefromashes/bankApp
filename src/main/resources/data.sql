INSERT INTO bank_user(id, username, password, authority, is_active, total_value, closed_accounts) 
	VALUES(0, 'admin', 'admin', 'ADMIN', TRUE, 0, '') 
	ON DUPLICATE KEY UPDATE authority = 'ADMIN';