package merit.capstone.bankApp.models;

import javax.persistence.Entity;
import javax.validation.constraints.Min;

import merit.capstone.bankApp.exceptions.TransactionNotAllowedException;

/*
 * Model class for CD accounts
 * CD accounts cannot be adjusted once created, as the funds are frozen until the term expires
 */
@Entity
public class CDAccount extends BankAccount{
	
	
	
	public CDAccount() {
		super();
		setAccountName("CD Account");
	}
	
	@Override
	public void withdraw(double amount) throws TransactionNotAllowedException { 
		throw new TransactionNotAllowedException();
	}
	
	@Override
	public void deposit(double amount) throws TransactionNotAllowedException {
		throw new TransactionNotAllowedException();
	}
    
}