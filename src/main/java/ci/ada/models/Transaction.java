package ci.ada.models;

import ci.ada.models.enumeration.TransactionType;

import java.util.Date;

public class Transaction {
    private Long id;
    private Float amount;
    private TransactionType transactionType;
    private Date transactionDate;
    private String description;
    private Account accoount;

    public Account getAccount() {
        return accoount;
    }

    public void setAccoount(Account accoount) {
        this.accoount = accoount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}