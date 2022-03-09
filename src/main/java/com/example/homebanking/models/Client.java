package com.example.homebanking.models;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<ClientLoans> clientLoans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    Set<Card> card = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public Set<Card> getCard() {
        return card;
    }

    public void setCard(Set<Card> card) {
        this.card = card;
    }

    public Set<ClientLoans> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoans> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public Set<Account> getAccount() {
        return accounts;
    }

    public void setAccount(Set<Account> account) {
        this.accounts = account;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void save(Client client) {

    }
}


