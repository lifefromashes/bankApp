package merit.capstone.bankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceedsAvailableBalanceException extends Exception{
	static final long serialVersionUID = 1002L;

	public ExceedsAvailableBalanceException(String msg) {
		super(msg);
	}
	
	public ExceedsAvailableBalanceException () {
		super("Amount is greater than available balance");
	}
}
