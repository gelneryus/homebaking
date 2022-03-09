package com.example.homebanking.DTOS;


import com.example.homebanking.models.Account;
import com.example.homebanking.models.TypeAccounts;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {

    private long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private boolean active;
    private TypeAccounts typeAccounts;
    Set<TransactionDTO> transaction;

    public AccountDTO() {
    }




    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transaction= account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        this.active = account.isActive();
        this.typeAccounts = account.getTypeAccounts();


    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TypeAccounts getTypeAccounts() {
        return typeAccounts;
    }

    public void setTypeAccounts(TypeAccounts typeAccounts) {
        this.typeAccounts = typeAccounts;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<TransactionDTO> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<TransactionDTO> transaction) {
        this.transaction = transaction;
    }
}
