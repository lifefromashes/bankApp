package merit.capstone.bankApp.models;

public class SavingsAccount extends BankAccount {

	static final double DEFAULT_INTEREST_RATE = .01;

	public SavingsAccount() {
		super();
		super.setInterestRate(DEFAULT_INTEREST_RATE);
	}
}