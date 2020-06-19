package merit.capstone.bankApp.models;

import javax.persistence.Entity;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;

@Entity
public class RolloverIRA extends BankAccount {
    //when distribute funds from old account from different IRA company
    //  have to request a direct rollover to avoid tax incursions
    //compare currnet investments with new range of options of investments

    static final double DEFAULT_INTEREST_RATE = 0;

    public RolloverIRA(){
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