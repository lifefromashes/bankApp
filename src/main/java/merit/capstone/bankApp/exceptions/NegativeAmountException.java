package merit.capstone.bankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeAmountException extends Exception {
	static final long serialVersionUID = 1001L;
	
	public NegativeAmountException(String msg) {
		super(msg);
	}
	
	public NegativeAmountException () {
		super("Negative amount not allowed");
	}
}
