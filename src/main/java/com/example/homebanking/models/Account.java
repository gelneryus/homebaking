package com.example.homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String number;
    private LocalDateTime creationDate ;
    private double balance;
    private boolean active = true;
    private TypeAccounts typeAccounts1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account",fetch= FetchType.EAGER)
    Set<Transactions> transactions = new HashSet<>();



    public long getId() {
        return id;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Account() {
    }

    public Account(String number, LocalDateTime creationDate, double balance,TypeAccounts typeAccounts1, Client client1,boolean active) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client=client1;
        this.active=active;
        this.typeAccounts1=typeAccounts1;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public TypeAccounts getTypeAccounts() {
        return typeAccounts1;
    }

    public void setTypeAccounts(TypeAccounts typeAccounts) {
        this.typeAccounts1 = typeAccounts;
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

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }


    public void addTransaction(Transaction transaction) {
    }


}

