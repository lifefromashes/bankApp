package merit.capstone.bankApp.models;

import java.util.List;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
import merit.capstone.bankApp.exceptions.TransactionNotAllowedException;

public abstract class IRAAccount extends BankAccount {

	@Override
	protected Transaction singleAccountTransaction(Transaction t) {
		try {
			if(t.getAmount() > 0){
				this.deposit(t.getAmount());
			}
			if(t.getAmount() < 0) {
				this.withdraw(-1 * t.getAmount());
				
				double pen = .2 * (t.getAmount() * -1);
				pen = Math.floor(pen * 100);
				pen = pen / 100;
				t.setTransactionMemo("20% Early Withdraw Penalty of $" + pen + " applied");
			}
			
			t.setTransactionSuccess(true);
			

		} catch (Exception e) {
			if(t.getAmount() < 0) {
				double v = t.getAmount() * -1;
				if(v <= this.getBalance() && v * 1.2 > this.getBalance()) {
					double m = this.getBalance() / 1.2;
					m = Math.floor(m * 100);
					m /= 100;
					t.setTransactionMemo("Unable to pay 20% Early Withdraw Fee. Max: $" + m);
				}
			}
			
			t.setTransactionSuccess(false);
		}

		return t;
	}

	// override withdraw
	@Override
	public void withdraw(double amount) throws ExceedsAvailableBalanceException, NegativeAmountException {
		double amountWithPenalty = amount * 1.2;
		amountWithPenalty = Math.floor(amountWithPenalty * 100);
		amountWithPenalty /= 100;

		if (amount > super.getBalance()) {
			throw new ExceedsAvailableBalanceException("Exceeds Available Balance.");
		}

		if (amountWithPenalty > super.getBalance()) {
			throw new ExceedsAvailableBalanceException("Exceeds Available Balance Once 20% penalty is added.");
		}

		if (amount < 0) {
			throw new NegativeAmountException("Unable to process.");
		}

		setBalance(getBalance() - amountWithPenalty);

	}

	@Override
	public Transaction closeAccount(BankUser user) throws TransactionNotAllowedException,
			ExceedsAvailableBalanceException, NegativeAmountException, CannotCloseAccountException {
		
		this.setActive(false);
		
		Transaction t = new Transaction();
		
		double m = this.getBalance() / 1.2;
		m = Math.floor(m * 100);
		m /= 100;
		
		t.setAmount(m);
		t.setTargetAccount(user.getSingleSavingsAccount().getAccountNumber());
		t.setSourceAccount(user.getSingleSavingsAccount().getAccountNumber());
		t.setTransactionSuccess(true);
		t.setTransactionMemo("Closed Account #" + getAccountNumber());
		
		setBalance(0);
		user.getSingleSavingsAccount().deposit(m);
		
		List <Transaction> lt = user.getSingleSavingsAccount().getTransactions();
		lt.add(t);
		user.getSingleSavingsAccount().setTransactions(lt);
		
		
		return t;
	}

	@Override
	protected Transaction multipleAccountTransaction(Transaction t, BankAccount source, BankAccount target) {
		boolean madeWithdraw = false;

		try {
			source.withdraw(t.getAmount());
			madeWithdraw = true;
			target.deposit(t.getAmount());
			t.setTransactionSuccess(true);

		} catch (Exception e) {
			if (madeWithdraw) {
				source.setBalance(source.getBalance() + t.getAmount() * 1.2);
			}
			t.setTransactionSuccess(false);

		}

		return t;
	}

}