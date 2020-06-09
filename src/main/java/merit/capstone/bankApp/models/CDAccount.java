package merit.capstone.bankApp.models;

import javax.validation.constraints.Min;

public class CDAccount extends BankAccount{
	
	
	
	public CDAccount() {
		super();
	}
	
	@Override
	public void withdraw(double amount) { //exceptions
		System.out.println("Unable to Withdraw from CD Account");
	}
	
	@Override
	public void deposit(double amount) { //exceptions
		System.out.println("Unable to deposit to CD Account");
	}
    
}