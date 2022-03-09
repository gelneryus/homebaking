package com.example.homebanking.DTOS;



import com.example.homebanking.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int password;
    private Set<AccountDTO> account;
    private Set<ClientLoanDTO> loans;
    private Set<CardDTO> card;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email =client.getEmail();
        this.account=client.getAccount().stream().map(AccountDTO::new).collect(Collectors.toSet());
        this.loans=client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
        this.card= client.getCard().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    public Set<CardDTO> getCard() {
        return card;
    }

    public void setCard(Set<CardDTO> card) {
        this.card = card;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(Set<ClientLoanDTO> loans) {
        this.loans = loans;
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

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public Set<AccountDTO> getAccount() {
        return account;
    }

    public void setAccount(Set<AccountDTO> account) {
        this.account = account;
    }
}
