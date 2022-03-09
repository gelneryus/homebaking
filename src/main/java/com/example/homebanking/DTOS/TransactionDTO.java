package com.example.homebanking.DTOS;

import com.example.homebanking.models.Transactions;
import com.example.homebanking.models.TransactionsType;


import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;
    private TransactionsType type;
    private Double amount;
    private String description;
    private LocalDateTime date;

    public TransactionDTO() {
    }

    public TransactionDTO(Transactions transactions) {
        this.id = transactions.getId();
        this.amount = transactions.getAmount();
        this.description = transactions.getDescription();
        this.date =transactions.getDate();
        this.type=transactions.getType();
    }

    public TransactionsType getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(TransactionsType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
