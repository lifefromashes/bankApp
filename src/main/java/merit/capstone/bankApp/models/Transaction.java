package merit.capstone.bankApp.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BankAccount sourceAccount;
    private BankAccount targeAccount;
    private Date transactionDate;
    private double amount;
    private String transactionMemo;
    private boolean transactionSuccess;

    public Transaction() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BankAccount getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(BankAccount sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public BankAccount getTargeAccount() {
        return targeAccount;
    }

    public void setTargeAccount(BankAccount targeAccount) {
        this.targeAccount = targeAccount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionMemo() {
        return transactionMemo;
    }

    public void setTransactionMemo(String transactionMemo) {
        this.transactionMemo = transactionMemo;
    }

    public boolean isTransactionSuccess() {
        return transactionSuccess;
    }

    public void setTransactionSuccess(boolean transactionSuccess) {
        this.transactionSuccess = transactionSuccess;
    }

    






    
    
}