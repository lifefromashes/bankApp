package merit.capstone.bankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MaxAccountsReachedException extends Exception {
static final long serialVersionUID = 1006L;
	
	public MaxAccountsReachedException(String msg) {
		super(msg);
	}
	
	public MaxAccountsReachedException () {
		super("Maximum number of this type of account already exist.");
	}

}
