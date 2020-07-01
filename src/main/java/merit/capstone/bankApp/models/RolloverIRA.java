package merit.capstone.bankApp.models;

import javax.persistence.Entity;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
/*
 * Model class to represent Rollover IRA accounts
 * client's specs asked for very little in terms of defining IRA accounts, so
 * no unique behavior is detailed here
 */
@Entity
public class RolloverIRA extends IRAAccount {
    //when distribute funds from old account from different IRA company
    //  have to request a direct rollover to avoid tax incursions
    //compare currnet investments with new range of options of investments

    static final double DEFAULT_INTEREST_RATE = 0;

    public RolloverIRA(){
        super();
        super.setInterestRate(DEFAULT_INTEREST_RATE);
        super.setMaxAccounts(1);
        setAccountName("Rollover IRA Account");

    }
   
    
}