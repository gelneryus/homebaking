package com.example.homebanking.controllers;


import com.example.homebanking.DTOS.PaymentsDTO;
import com.example.homebanking.models.*;
import com.example.homebanking.repositories.AccountRepository;
import com.example.homebanking.repositories.CardRepository;
import com.example.homebanking.repositories.ClientRepository;
import com.example.homebanking.repositories.TransactionRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    @PostMapping("/payments")
    public ResponseEntity<Object> paymentDTOList(@RequestBody PaymentsDTO paymentsDTO, Authentication authentication){

        Client client = clientRepository.findByEmail(authentication.getName());
        Card card = cardRepository.findByNumber(paymentsDTO.getNumber());
        List<Account> accountList = client.getAccount().stream().collect(Collectors.toList());
        List<Account> accountBalance = accountList.stream().filter(account -> account.getBalance() > paymentsDTO.getAmount()).collect(Collectors.toList());
        Account account = accountBalance.stream().findFirst().orElse(null);

        //Account account = accountRepository.findById(card.getAccount().getId()).orElse(null);
        //List<Card> cardList = client.getCards().stream().filter(card1 -> card.getNumber() == paymentDTO.getNumber()).collect(Collectors.toList());




        if(paymentsDTO == null ){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if(paymentsDTO.getNumber().isEmpty() || paymentsDTO.getCvv() != card.getCvv() || paymentsDTO.getAmount() < 0 || paymentsDTO.getDescription().isEmpty()){
            return new ResponseEntity<>("Payment data is incorrect", HttpStatus.FORBIDDEN);
        }



        if(paymentsDTO.getName() == client.getFirstName() + " " + client.getLastName()){
            return new ResponseEntity<>("Name doesn't match", HttpStatus.FORBIDDEN);
        }

        String cardThruDate = card.toString().substring(5, 7) + '/' + card.getTruDate().toString().substring(0,4);
        String paymentThruDate = paymentsDTO.getThruDate().toString();

        System.out.println(cardThruDate.equals(paymentThruDate) );

        if(!cardThruDate.equals(paymentThruDate)){
            return new ResponseEntity<>("Thru date is incorrect", HttpStatus.FORBIDDEN);
        }

        if(account == null){
            return new ResponseEntity<>("la cuenta no existe", HttpStatus.FORBIDDEN);
        }

        if(account.getBalance() < paymentsDTO.getAmount()){
            return new ResponseEntity<>("FOndos insuficientes", HttpStatus.FORBIDDEN);
        }

        if(!card.getEsActiva() && !account.isActive()){
            return new ResponseEntity<>("la tarjeta ya no se puede usar", HttpStatus.FORBIDDEN);
        }



        if(card.getTruDate().isBefore(LocalDateTime.now())){
            return new ResponseEntity<>("Card expired", HttpStatus.FORBIDDEN);
        }


        Transactions transaction = new Transactions(TransactionsType.DEBITO, paymentsDTO.getAmount(), paymentsDTO.getDescription(), LocalDateTime.now(), account);
        account.setBalance(account.getBalance() - paymentsDTO.getAmount());
        transactionRepository.save(transaction);
        accountRepository.save(account);

        return new ResponseEntity<>("Transaction complete", HttpStatus.ACCEPTED);


    }


}