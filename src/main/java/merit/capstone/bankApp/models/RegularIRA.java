package merit.capstone.bankApp.models;

import javax.persistence.Entity;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;

@Entity
public class RegularIRA extends IRAAccount {

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
        setAccountName("IRA Account");
    }
  

   

    
}