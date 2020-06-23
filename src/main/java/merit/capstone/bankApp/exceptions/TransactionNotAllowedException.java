package merit.capstone.bankApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionNotAllowedException extends Exception {
	

	private static final long serialVersionUID = 1008L;

	public TransactionNotAllowedException(String msg) {
		super(msg);
	}
	
	public TransactionNotAllowedException() {
		super("Unable to complete transactions on CDAccounts");
	}

}
