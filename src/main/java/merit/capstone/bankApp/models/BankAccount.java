package merit.capstone.bankApp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;


@Entity
public abstract class BankAccount {


    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber;

	@Min(0)
	private double balance;
    
    @DecimalMin("0.0")
	@DecimalMax("0.99999")
	private double interestRate;

    private Date accountOpenedOn;
    
    private int term;
    
    private long userId;
    
	private List<CDOffering> cdOfferings;
	
	private int maxAccounts = 0;

	public BankAccount() {
		this.accountOpenedOn = new Date();
	}

	public BankAccount(double balance, double interestRate, long accountNumber, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
	}


    public void withdraw(double amount) throws ExceedsAvailableBalanceException, NegativeAmountException {
        if(amount > this.balance) {
            throw new ExceedsAvailableBalanceException("Exceeds Available Balance");
        } 
         if(amount < 0) {
            throw new NegativeAmountException("Unable to process");
        }
        this.balance -= amount;
    }

    public void deposit(double amount) throws ExceedsAvailableBalanceException, NegativeAmountException {
      if(amount > this.balance) {
          throw new ExceedsAvailableBalanceException("Exceeds Available Balance");
      } 
       if(amount < 0) {
          throw new NegativeAmountException("Unable to process");
      }
      this.balance += amount;
    }

    public BankAccount closeAccount(BankAccount closingAccount, BankAccount receivingAccount) throws ExceedsAvailableBalanceException, NegativeAmountException{
        double amount = closingAccount.balance;
        closingAccount.withdraw(amount);
        closingAccount.deposit(amount);
        return closingAccount;
    }

    public double futureValue(int years) {
		if(years == 0) { return this.balance; }
		return futureValue(years - 1) * (1 + this.interestRate);
	}

	

	public CDOffering cdOfferings() {
		return cdOfferings();
	}
    
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Date getAccountOpenedOn() {
		return accountOpenedOn;
	}

	public void setAccountOpenedOn(Date accountOpenedOn) {
		this.accountOpenedOn = accountOpenedOn;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<CDOffering> getCdOfferings() {
		return cdOfferings;
	}

	public void setCdOfferings(List<CDOffering> cdOfferings) {
		this.cdOfferings = cdOfferings;
	}

	public int getMaxAccounts() {
		return maxAccounts;
	}

	public void setMaxAccounts(int maxAccounts) {
		this.maxAccounts = maxAccounts;
	}
	
    
    
}
