package com.example.homebanking.controllers;


import com.example.homebanking.DTOS.CardDTO;

import com.example.homebanking.models.Card;
import com.example.homebanking.models.CardColor;
import com.example.homebanking.models.Client;
import com.example.homebanking.models.TransactionsType;
import com.example.homebanking.repositories.CardRepository;

import com.example.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ClientRepository clientRepository;

    @RequestMapping("/cards")
    public List<CardDTO> cardDTo() {
        return cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/cards/{id}")
    public CardDTO getcardDTO(@PathVariable Long id) {
        CardDTO cardId = new CardDTO(cardRepository.findById(id).orElse(null));
        return cardId;
    }

    int min2 = 0001;
    int max2 = 9999;

    public int getRandomCardNumber(int min2, int max2) {
        return (int) ((Math.random() * (max2 - min2)) - min2);
    }

    public String getRandomCardNumber() {
        int randomCardNumber = getRandomCardNumber(min2, max2);
        return String.valueOf(randomCardNumber);
    }

    public String getRandomStringCard() {
        String cardNumber = "";
        for (int i = 0; i <= 4; i++) {
            String targserie = getRandomCardNumber();
            cardNumber += ("-" + targserie);
        }
        return cardNumber.substring(1);
    }

    int min1 = 100;
    int max1 = 999;

    public int getRandomNumber(int min1, int max1) {
        return (int) ((Math.random() * (this.max1 - this.min1)) + this.min1);
    }

    @RequestMapping(path = "clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> createCard(Authentication authentication,
                                             @RequestParam TransactionsType cardType, @RequestParam CardColor cardColor) {

        Client clientCurrent1 = clientRepository.findByEmail(authentication.getName());

        List<Card> listCard = clientCurrent1.getCard().stream().filter(card -> {
            return card.getType() == cardType;
        }).collect(Collectors.toList());

        if (listCard.size() == 3) {
            return new ResponseEntity<>("403 prophibido", HttpStatus.FORBIDDEN);
        }
        String cardNumber = getRandomStringCard();
        int cvv = getRandomNumber(100, 999);

        LocalDateTime thruDate = LocalDateTime.now();
        LocalDateTime fromDate = thruDate.plusYears(5);

        Card cards1 = new Card(clientCurrent1.getFirstName() + "" + clientCurrent1.getLastName(), cardType, cardColor, cardNumber, cvv, fromDate, thruDate, clientCurrent1, true);
        cardRepository.save(cards1);
        return new ResponseEntity<>("has creado una tarjeta con exito", HttpStatus.CREATED);
    }

    //Metodo para borrar una tarjeta, que en realidad cambia una variable boolean que dice si está activa o no, para que aparezca comno si estuviera borrada pero sigue estando en la BD
    @PatchMapping("/clients/current/cards/delete/{id}")
    public ResponseEntity<Object> smartDelete(@PathVariable Long id){
        Card card = cardRepository.findById(id).orElse(null);
        card.setEsActiva(false);
        cardRepository.save(card);
        return new ResponseEntity<>("Set Good: false", HttpStatus.CREATED);
    }

}







