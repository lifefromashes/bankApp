package merit.capstone.bankApp.models;

import javax.persistence.Entity;

/*
 * Model class representing a Checking Account. Other than the low default interest rate,
 * checking accounts have no special rules 
 */
@Entity
public class CheckingAccount extends BankAccount{
	
	static final double DEFAULT_INTEREST_RATE = .0001;

	
	public CheckingAccount() {
		super(); 
		super.setInterestRate(DEFAULT_INTEREST_RATE);
		super.setMaxAccounts(1);
		setAccountName("Checking Account");
	}

	

	
	
    
}