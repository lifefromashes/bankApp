package merit.capstone.bankApp.models;

import java.sql.Date;
import java.time.Instant;

public class Transactions {

    BankAccount sourceAccount;
    BankAccount targeAccount;
    Date transactionDate;
    double amount;
    String transactionMemo;

    //do we want this as our time??
    private Instant createdAt;
    private Instant updatedAt;

    private boolean transactionSuccess;


    
    
}