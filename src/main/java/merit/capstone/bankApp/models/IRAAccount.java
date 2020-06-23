package merit.capstone.bankApp.models;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;

public abstract class IRAAccount extends BankAccount {

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
         user.getSingleSavingsAccount().deposit(this.getBalance());
         this.setBalance(0);
         this.setActive(false);
         return this;
     }
 
     /*
     @Override
     public Transaction processTransaction(Transaction t) {
         if(t.getTargeAccount().equals(t.getSourceAccount())){
             t = singleAccountTransaction(t);
         } 
 
         t.setBalanceAfterTransaction(this.getBalance());
         transactions.add(t);
 
         return t;
     }
     */
    
}