package com.example.homebanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private TransactionsType type;
    private Double amount;
    private String description;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    public Transactions() {
    }


    public Transactions(TransactionsType type, Double amount, String description, LocalDateTime date ,Account account) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.account=account;
    }

    public Transactions(TransactionsType debito, double monto, String descriptionDebits, LocalDateTime now) {
    }

    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionsType getType() {
        return type;
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

    public long getId() {
        return id;
    }
    public void save(Transactions transactions){
    }

}
