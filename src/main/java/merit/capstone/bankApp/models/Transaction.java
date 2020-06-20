package merit.capstone.bankApp.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@OneToOne(cascade = CascadeType.ALL)
    private long sourceAccount;

    //@OneToOne(cascade = CascadeType.ALL)
    private long targeAccount;

    private Date transactionDate;
    private double amount;
    private String transactionMemo;
    private boolean transactionSuccess;

    private double balanceAfterTransaction;

    public Transaction() {
    	this.transactionDate = new Date();
    	this.amount = 0;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public long getSourceAccount() { return sourceAccount; }
    public void setSourceAccount(long sourceAccount) { this.sourceAccount = sourceAccount; }
    public long getTargeAccount() { return targeAccount; }
    public void setTargeAccount(long targeAccount) { this.targeAccount = targeAccount; }
    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getTransactionMemo() { return transactionMemo; }
    public void setTransactionMemo(String transactionMemo) { this.transactionMemo = transactionMemo; }
    public boolean isTransactionSuccess() { return transactionSuccess; }
    public void setTransactionSuccess(boolean transactionSuccess) { this.transactionSuccess = transactionSuccess; }
    public double getBalanceAfterTransaction() { return balanceAfterTransaction; }
    public void setBalanceAfterTransaction(double balanceAfterTransaction) { this.balanceAfterTransaction = balanceAfterTransaction; }

    






    
    
}