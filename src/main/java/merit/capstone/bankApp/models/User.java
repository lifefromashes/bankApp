package merit.capstone.bankApp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * This model class represents an individual bank customer or employee
 * 
 * Each Bank Account object should only be accessible through its user
 *
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String username;
	
	private String password;
	
	private String authority;

	private List<BankAccount> bankAccounts;

	private String firstName;

	private String lastName;

	private String ssn;

	private String email;

	private String phone;

	private String address;

	private String city;

	private String state;

	private String zip;

	public User(){}

	public User(String username, String password, String authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

	public User(String username, String password, String authority, String firstName, String lastName, String ssn) {
		this.username = username;
		this.password = password;
		this.authority = authority;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
	}

	public User(String firstName, String lastName, String ssn, String phone, String email,
				String address, String city, String state, String zip){
					this.firstName = firstName;
					this.lastName = lastName;
					this.ssn = ssn;
					this.email = email;
					this.address = address;
					this.phone = phone;
					this.city = city;
					this.state = state;
					this.zip = zip;
	}

	public BankAccount addBankAccount(double openingBalance){ //add throws NegAmtException and others
		if(openingBalance < 0) {
			System.out.println("Can't Deposit Negative Amount"); //add exception
		}
		//add correct code
		return null;
	}

	public List<BankAccount> getCheckingAccounts() {
		List<BankAccount> accounts = new ArrayList<>();
		for(BankAccount b : this.bankAccounts){
		  if(b instanceof CheckingAccount){
			accounts.add(b);
		  }
		}
		return accounts;
	  }

	  public List<BankAccount> getSavingsAccount() {
		List<BankAccount> accounts = new ArrayList<>();
		for(BankAccount b : this.bankAccounts){
		  if(b instanceof SavingsAccount){
			accounts.add(b);
		  }
		}
		return accounts;
	  }

	  public List<BankAccount> getCDAccount() {
		List<BankAccount> accounts = new ArrayList<>();
		for(BankAccount b : this.bankAccounts){
		  if(b instanceof CDAccount) {
			accounts.add(b);
		  }
		}
		return accounts;
	  }


	
	

}
