package merit.capstone.bankApp.models;

import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
public class CDAccount extends BankAccount{
	
	
	
	public CDAccount() {
		super();
		setAccountName("CD Account");
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