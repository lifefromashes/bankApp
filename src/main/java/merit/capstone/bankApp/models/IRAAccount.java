package merit.capstone.bankApp.models;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
import merit.capstone.bankApp.exceptions.TransactionNotAllowedException;
import merit.capstone.bankApp.repos.BankAccountRepository;

public abstract class IRAAccount extends BankAccount {

	

	// override withdraw
	@Override
	public void withdraw(double amount) throws ExceedsAvailableBalanceException, NegativeAmountException {
		double amountWithPenalty = amount * 1.2;

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
	public BankAccount closeAccount(BankUser user) throws TransactionNotAllowedException,
			ExceedsAvailableBalanceException, NegativeAmountException, CannotCloseAccountException {
		setBalance(getBalance() * .8);
		user.getSingleSavingsAccount().deposit(this.getBalance());
		this.setBalance(0);
		this.setActive(false);
		return this;
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