package merit.capstone.bankApp.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotCloseAccountException extends Exception {

   
	private static final long serialVersionUID = 1009L;

	public CannotCloseAccountException(String msg) {
        super(msg);
    }

    public CannotCloseAccountException() {
        super("Unable to close Savings Account.");
    }


    
}