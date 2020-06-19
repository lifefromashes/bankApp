package merit.capstone.bankApp.models;

import javax.persistence.Entity;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;

@Entity
public class RegularIRA extends BankAccount {

    //Can contribute up to 6k per year
    //if 50 or older can contribrute 1k extra per year
    //If you have more than 1 IRA the total contribution to all your
    //    IRA's cant exceed annual limit
    //can set up automatic investments

    //withdrawals prior to age 59.5 subject to 10% penalty plus fed and state taxes
    //withdraw b/w 59.5 & 72--no penalty, but pay taxes
    //withdraw 72+ must make withdrawals called RMDs

	static final double DEFAULT_INTEREST_RATE = 0;

    public RegularIRA() {
        super();
        super.setInterestRate(DEFAULT_INTEREST_RATE);
        super.setMaxAccounts(1);
    }

    //override withdraw
    @Override
    public void withdraw(double amount) throws ExceedsAvailableBalanceException, NegativeAmountException {
        double amountWithPenalty = amount * 1.2;

        if(amount > super.getBalance()) {
            throw new ExceedsAvailableBalanceException("Exceeds Available Balance.");
        } 

        if(amountWithPenalty> super.getBalance()) {
            throw new ExceedsAvailableBalanceException("Exceeds Available Balance Once 20% penalty is added.");
        } 

         if(amount < 0) {
            throw new NegativeAmountException("Unable to process.");
         }

         super.setBalance(super.getBalance() - amountWithPenalty);

    }

    @Override
    public BankAccount closeAccount(BankUser user) throws ExceedsAvailableBalanceException, NegativeAmountException, CannotCloseAccountException{
        setBalance(getBalance() * .8);
        return super.closeAccount(user);
    }


    @Override
    public Transaction processTransaction(Transaction t) {
		if(t.getTargeAccount().equals(t.getSourceAccount())){
			t = singleAccountTransaction(t);
		} 

		t.setBalanceAfterTransaction(this.getBalance());
		transactions.add(t);

		return t;
	}

    
}