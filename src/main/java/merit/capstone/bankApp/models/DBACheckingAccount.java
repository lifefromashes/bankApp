package merit.capstone.bankApp.models;

public class DBACheckingAccount extends BankAccount {

	static final double DEFAULT_INTEREST_RATE = .0002;

	public DBACheckingAccount() {
		super();
		super.setInterestRate(DEFAULT_INTEREST_RATE);
	}
}