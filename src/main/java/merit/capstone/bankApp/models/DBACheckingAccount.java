package merit.capstone.bankApp.models;

import javax.persistence.Entity;
/*
 * Model class to represent business checking accounts
 * users are limited to a max of 3 of these
 */
@Entity
public class DBACheckingAccount extends BankAccount {

	static final double DEFAULT_INTEREST_RATE = .0002;


	public DBACheckingAccount() {
		super();
		super.setInterestRate(DEFAULT_INTEREST_RATE);
		super.setMaxAccounts(3);
		setAccountName("DBA Checking Account");
	}
}