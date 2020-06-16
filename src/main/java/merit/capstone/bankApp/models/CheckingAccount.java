package merit.capstone.bankApp.models;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends BankAccount{
	
	static final double DEFAULT_INTEREST_RATE = .0001;

	
	public CheckingAccount() {
		super(); 
		super.setInterestRate(DEFAULT_INTEREST_RATE);
		super.setMaxAccounts(1);
	}

	@Override
	public void deposit(double amount) {
		System.out.println("deposited");
	}

	
	
    
}