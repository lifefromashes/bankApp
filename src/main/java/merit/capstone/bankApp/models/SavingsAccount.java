package merit.capstone.bankApp.models;

import javax.persistence.Entity;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
/*
 * model class to represent savings accounts
 * 
 * savings accounts are unique in that each user will have exactly one, and it cannot
 * be deleted without the user closing their entire account
 * (so that other closed accounts always have a place to deposit funds)
 */
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