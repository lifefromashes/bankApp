package merit.capstone.bankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceedsCombinedBalanceLimitException extends Exception{
	static final long serialVersionUID = 1003L;
	
	public ExceedsCombinedBalanceLimitException(String msg) {
		super(msg);
	}
	
	public ExceedsCombinedBalanceLimitException () {
		super("Cannot have combined checking and savings account balances >250,000");
	}
	
	
}
