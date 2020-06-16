package merit.capstone.bankApp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Check;

import javassist.expr.Instanceof;
import merit.capstone.bankApp.exceptions.MaxAccountsReachedException;



/**
 * 
 * This model class represents an individual bank customer or employee
 * 
 * Each Bank Account object should only be accessible through its user
 *
 */
@Entity
public class BankUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String username;
	private String password;
	private String authority;
	private String firstName;
	private String lastName;
	private String ssn;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<BankAccount> bankAccounts;

	

	public BankUser() {
		bankAccounts = new ArrayList<>();
	}

	public BankAccount addBankAccount(BankAccount bankAccount) throws MaxAccountsReachedException { // add throws NegAmtException and others

		if (bankAccount.getBalance() < 0) {
			System.out.println("Can't Deposit Negative Amount"); // add exception
		}		
		if(getNumberOfAccountsByType(bankAccount) <= bankAccount.getMaxAccounts()){
			throw new MaxAccountsReachedException();
		}		
		bankAccounts.add(bankAccount);
		return bankAccount;
	}

	public int getNumberOfAccountsByType(BankAccount type) {
		int sum = 0;
		for(BankAccount b : this.bankAccounts) {
			if(b.getClass() == type.getClass()) {
				sum++;
			}
		}
		return sum;
	}

	

	public List<BankAccount> getCheckingAccounts() {
		List<BankAccount> accounts = new ArrayList<>();
		for (BankAccount b : this.bankAccounts) {
			if (b instanceof CheckingAccount) {
				accounts.add(b);
			}
		}
		return accounts;
	}

	public List<BankAccount> getDBACheckingAccounts() {
		List<BankAccount> accounts = new ArrayList<>();
		for (BankAccount b : this.bankAccounts) {
			if (b instanceof DBACheckingAccount) {
				accounts.add(b);
			}
		}
		return accounts;
	}

	public List<BankAccount> getSavingsAccount() {
		List<BankAccount> accounts = new ArrayList<>();
		for (BankAccount b : this.bankAccounts) {
			if (b instanceof SavingsAccount) {
				accounts.add(b);
			}
		}
		return accounts;
	}

	public List<BankAccount> getCDAccount() {
		List<BankAccount> accounts = new ArrayList<>();
		for (BankAccount b : this.bankAccounts) {
			if (b instanceof CDAccount) {
				accounts.add(b);
			}
		}
		return accounts;
	}

	public double getBalanceByType(BankAccount type) {
		double sum = 0;
		for (BankAccount b : bankAccounts) {
			if (b.getClass() == type.getClass()) {
				sum += b.getBalance();
			}
		}
		return sum;
	}

	public double getCombinedBalance() {

		double sum = 0;
		sum += getBalanceByType(new CheckingAccount());
		sum += getBalanceByType(new SavingsAccount());
		sum += getBalanceByType(new CDAccount());
		sum += getBalanceByType(new DBACheckingAccount());
		return sum;
	}

	//can't withdraw from IRA or CD accounts so don't include here.
	public double getAvailableBalanceByType(BankAccount type) throws MaxAccountsReachedException {
		double sum = 0;

		for(BankAccount b : bankAccounts) {
			//****clean up the if statement. Maybe do switch or something, throw exception?????
			// if(b.getMaxAccounts() != 0 && b.getMaxAccounts() >= type.getMaxAccounts()) {
			// 	throw new MaxAccountsReachedException();
			// }
			if(b instanceof CDAccount || b instanceof RegularIRA || b instanceof RolloverIRA || b instanceof RothIRA){
				System.out.println("Can not access available balance for type of account");
			} else if (b.getClass() == type.getClass()){
				sum += b.getBalance();
			}
		}
		return sum;
	}
	
	

	public void updateContactInfo(BankUser temp) {
		this.address = temp.getAddress();
		this.city = temp.getCity();
		this.zip = temp.getZip();
		this.phone = temp.getPhone();
		this.email = temp.getEmail();
	}

	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getAuthority() { return authority; }
	public void setAuthority(String authority) { this.authority = authority; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getSsn() { return ssn; }
	public void setSsn(String ssn) { this.ssn = ssn; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }
	public String getZip() { return zip; }
	public void setZip(String zip) { this.zip = zip; }
	public List<BankAccount> getBankAccounts() { return bankAccounts; }
	public void setBankAccounts(List<BankAccount> bankAccounts) { this.bankAccounts = bankAccounts; }



}
