package merit.capstone.bankApp.models;

import javax.persistence.Entity;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;

@Entity
public class SavingsAccount extends BankAccount {

	static final double DEFAULT_INTEREST_RATE = .01;
	private int maxAccounts = 1;

	public SavingsAccount() {
		super();
		super.setInterestRate(DEFAULT_INTEREST_RATE);
		super.setMaxAccounts(1);
		setAccountName("Savings Account");
	}

	@Override
	public Transaction closeAccount(BankUser user) throws ExceedsAvailableBalanceException, NegativeAmountException, CannotCloseAccountException{
		throw new CannotCloseAccountException();
	}
}