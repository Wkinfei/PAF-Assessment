package nus.iss.pafassessment.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Account {
    private String fromAccountId;
    private String toAccountId;
    private String name;
    private Float balance;
    private Float amount;
    private String transactionId;
    
  
    
    @Override
    public String toString() {
        return "Account [fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId + ", name=" + name
                + ", balance=" + balance + ", amount=" + amount + ", transactionId=" + transactionId + "]";
    }
    public String getFromAccountId() {
        return fromAccountId;
    }
    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    public String getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    
}
