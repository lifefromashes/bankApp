package merit.capstone.bankApp.models;

import javax.persistence.Entity;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
/*
 * Model class to represent Roth IRA accounts
 * client's specs asked for very little in terms of defining IRA accounts, so
 * no unique behavior is detailed here
 */
@Entity
public class RothIRA extends IRAAccount {
    //no RMDs for Roths
    //withdraws are tax and penalty free provided
    //    5 year aging requirement &
    //    over age 59.5
    //Nonqualified is subject to taxaction and 10% additional tax 
    //  unless exception applies
    static final double DEFAULT_INTEREST_RATE = 0;
    

    public RothIRA() {
        super();
        super.setInterestRate(DEFAULT_INTEREST_RATE);
        super.setMaxAccounts(1);
        setAccountName("Roth IRA Account");
    }
  

}