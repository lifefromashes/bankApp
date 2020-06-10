package merit.capstone.bankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameAlreadyExistsException extends Exception {
static final long serialVersionUID = 1005L;
	
	public UsernameAlreadyExistsException(String msg) {
		super(msg);
	}
	
	public UsernameAlreadyExistsException () {
		super("Username already exists");
	}

}
