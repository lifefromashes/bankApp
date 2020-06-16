package merit.capstone.bankApp.models;

import javax.persistence.Entity;

@Entity
public class SavingsAccount extends BankAccount {

	static final double DEFAULT_INTEREST_RATE = .01;
	private int maxAccounts = 1;

	public SavingsAccount() {
		super();
		super.setInterestRate(DEFAULT_INTEREST_RATE);
		super.setMaxAccounts(1);
	}
}