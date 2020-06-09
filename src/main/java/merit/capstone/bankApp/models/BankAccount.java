package merit.capstone.bankApp.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;


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


    public void withdraw(double amount) {
        if(amount > this.balance) {
            //throw new ExceedsAvailableBalanceException("Exceeds Available Balance");
            System.out.println("Unable to withdraw");
        } 
         if(amount < 0) {
            //throw new NegativeAmountException("Unable to process");
            System.out.println("Unable to process");
        }
        this.balance -= amount;
    }

    public void deposit(double amount) {
      if(amount > this.balance) {
          //throw new ExceedsAvailableBalanceException("Exceeds Available Balance");
          System.out.println("Unable to withdraw");
      } 
       if(amount < 0) {
          //throw new NegativeAmountException("Unable to process");
          System.out.println("Unable to process");
      }
      this.balance += amount;
    }

    public BankAccount closeAccount(BankAccount closingAccount, BankAccount receivingAccount) {
        double amount = closingAccount.balance;
        closingAccount.withdraw(amount);
        closingAccount.deposit(amount);
        return closingAccount;
    }

    public double futureValue(int years) {
		if(years == 0) { return this.balance; }
		return futureValue(years - 1) * (1 + this.interestRate);
	}
}
