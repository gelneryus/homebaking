package com.example.homebanking.DTOS;


import com.example.homebanking.models.*;

import java.time.LocalDateTime;

public class CardDTO {
    private long id;
    private String cardHolder;
    private TransactionsType type;
    private CardColor cardColor;
    private String number;
    private Integer cvv;
    private LocalDateTime fromDate;
    private LocalDateTime truDate;
    private Boolean esActiva;


    public CardDTO() {
    }



    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.type=card.getType();
        this.cardColor =card.getCardColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.truDate = card.getTruDate();
        this.esActiva =card.getEsActiva();
    }

    public Boolean getEsActiva() {
        return esActiva;
    }

    public void setEsActiva(Boolean esActiva) {
        this.esActiva = esActiva;
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

    public void setCvv(Integer cvv) {
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
}
