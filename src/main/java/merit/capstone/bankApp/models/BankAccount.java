package merit.capstone.bankApp.models;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
import merit.capstone.bankApp.exceptions.TransactionNotAllowedException;
import merit.capstone.bankApp.repos.BankAccountRepository;


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
    
	private int maxAccounts;

	@OneToMany(cascade = CascadeType.ALL)
	protected List<Transaction> transactions;

	private boolean isActive;
	
	private String accountName;

	public BankAccount() {
		this.maxAccounts = 0;
		this.balance = 0;
		this.accountOpenedOn = new Date();
		transactions = new ArrayList<Transaction>();
		this.isActive = true;
		this.term = -1;
		
	}
	

	
	public Transaction processTransaction(Transaction t, BankAccount source, BankAccount target) {
		if(source.equals(target)){
			t = singleAccountTransaction(t);
		} else {
			t = multipleAccountTransaction(t, source, target);
		}

		t.setBalanceAfterTransaction(this.getBalance());
		this.transactions.add(t);

		return t;
	}

	protected Transaction singleAccountTransaction(Transaction t) {
		try {
			if(t.getAmount() > 0){
				this.deposit(t.getAmount());
			}
			if(t.getAmount() < 0) {
				this.withdraw(-1 * t.getAmount());
			}
			
			t.setTransactionSuccess(true);

		} catch (Exception e) {
			t.setTransactionSuccess(false);
		}

		return t;
	}

	protected Transaction multipleAccountTransaction(Transaction t, BankAccount source, BankAccount target) {
		boolean madeWithdraw = false;
		
		try {
			source.withdraw(t.getAmount());
			madeWithdraw = true;
			target.deposit(t.getAmount());
			t.setTransactionSuccess(true);
		} catch (Exception e) {
			if(madeWithdraw) {
				source.setBalance(source.getBalance() + t.getAmount());
			}
			t.setTransactionSuccess(false);

		}

		
		return t;
	}
	
	


    public void withdraw(double amount) throws TransactionNotAllowedException, ExceedsAvailableBalanceException, NegativeAmountException {
        if(amount > this.balance) {
            throw new ExceedsAvailableBalanceException("Exceeds Available Balance");
        } 
         if(amount < 0) {
            throw new NegativeAmountException("Unable to process");
        }
        this.balance -= amount;
    }

    public void deposit(double amount) throws TransactionNotAllowedException, ExceedsAvailableBalanceException, NegativeAmountException {
      
       if(amount < 0) {
          throw new NegativeAmountException("Unable to process");
      }
      this.balance += amount;
    }

    public Transaction closeAccount(BankUser user) throws TransactionNotAllowedException, ExceedsAvailableBalanceException, NegativeAmountException, CannotCloseAccountException{
		
    	BankAccount targetAccount = user.getSingleSavingsAccount();
		Transaction t = null;
		double amount = this.balance;
		
		this.isActive = false;
		try {
			withdraw(amount);
			targetAccount.deposit(amount);
			
			t = new Transaction();
			t.setAmount(amount);
			t.setTargetAccount(targetAccount.getAccountNumber());
			t.setSourceAccount(targetAccount.getAccountNumber());
			t.setTransactionSuccess(true);
			t.setTransactionMemo("Closed Account #" + this.accountNumber);
			t.setBalanceAfterTransaction(targetAccount.getBalance());
			
			List <Transaction> lt = targetAccount.getTransactions();
			lt.add(t);
			targetAccount.setTransactions(lt);
			
			
		} catch (Exception expected) {
			//closing a CD account should not give any funds
		}
        
        return t;
    }

    public double futureValue(int years) {
		if(years == 0) { return this.balance; }
		return futureValue(years - 1) * (1 + this.interestRate);
	}

	
    
    public void addTransaction(Transaction t) {
    	this.transactions.add(t);
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

	

	public int getMaxAccounts() {
		return maxAccounts;
	}

	public void setMaxAccounts(int maxAccounts) {
		this.maxAccounts = maxAccounts;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
    
    
}
