package com.example.homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String cardHolder;
    private TransactionsType type;
    private CardColor cardColor;
    private String number;
    private  int  cvv;
    private LocalDateTime fromDate;
    private LocalDateTime truDate;
    private Boolean esActiva=true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Card() {
    }

    public Card(String cardHolder, TransactionsType type, CardColor cardColor, String number, int cvv, LocalDateTime fromDate, LocalDateTime truDate, Client client,boolean activa) {
        this.cardHolder = cardHolder;
        this.type = type;
        this.cardColor = cardColor;
        this.number = number;
        this.cvv = cvv;
        this.fromDate = fromDate;
        this.truDate = truDate;
        this.client = client;
        this.esActiva=activa;
    }


    public Boolean getEsActiva() {
        return esActiva;
    }

    public void setEsActiva(Boolean esActiva) {
        this.esActiva = esActiva;
    }

    @JsonIgnore

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public TransactionsType getType() {
        return type;
    }

    public void setType(TransactionsType type) {
        this.type = type;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getTruDate() {
        return truDate;
    }

    public void setTruDate(LocalDateTime truDate) {
        this.truDate = truDate;
    }

    public LocalDateTime getExpireDateThru() {
        return null;
    }


}
