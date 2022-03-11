package com.example.homebanking.controllers;

import com.example.homebanking.DTOS.TransactionDTO;
import com.example.homebanking.models.Account;
import com.example.homebanking.models.Client;
import com.example.homebanking.models.Transactions;
import com.example.homebanking.models.TransactionsType;
import com.example.homebanking.repositories.AccountRepository;
import com.example.homebanking.repositories.ClientRepository;
import com.example.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.homebanking.models.TransactionsType.CREDITO;
import static com.example.homebanking.models.TransactionsType.DEBITO;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    @RequestMapping(path = "/transactions",method = RequestMethod.POST)
    public ResponseEntity<Object> createdTransaction(Authentication authentication,
                                                     @RequestParam Double amount,@RequestParam String description,
                                                     @RequestParam String numberDestiny,@RequestParam String numberOrigin) {


        Client client = clientRepository.findByEmail(authentication.getName());
        Account accountDestiny=accountRepository.findByNumber(numberDestiny);
        Account accountOrigin=accountRepository.findByNumber(numberOrigin);
        //veirfico que los parametros no esten vacios

        if ((amount == null) || (description == null) || (numberDestiny == null) || (numberOrigin == null)){
            return new ResponseEntity<>("403 parametros vacios", HttpStatus.FORBIDDEN);
        }
    if (numberOrigin.equals(numberDestiny)){
    return new ResponseEntity<>("Las cuentas son iguales 403", HttpStatus.FORBIDDEN);
}

    if (accountOrigin==null){
        return new ResponseEntity<>("403 la cuenta no existe",HttpStatus.FORBIDDEN);
    }

    Set<Account> setNumberOrigin= client.getAccount();
    if (setNumberOrigin.contains(numberOrigin)) {
        return new ResponseEntity<>(" 403 CUENTA NO AUTENTICADA", HttpStatus.FORBIDDEN);
        }

        if (accountDestiny==null){
            return new ResponseEntity<>("cuenta de destino no existe",HttpStatus.FORBIDDEN);
    }
if (accountOrigin.getBalance()< amount || amount <= 0){
    return new ResponseEntity<>("No tiene balance suficiente para hacer la transaccion",HttpStatus.FORBIDDEN);
    }



Transactions transactionsOrigin=new Transactions(CREDITO,amount,accountOrigin.getNumber()+""+description, LocalDateTime.now(),accountOrigin);
Transactions transactionsDestiny=new Transactions(DEBITO,amount,accountDestiny.getNumber()+""+description,LocalDateTime.now(),accountDestiny);
transactionRepository.save(transactionsOrigin);
transactionRepository.save(transactionsDestiny);

//descuento de las cuentas
    Double auxOrigin=accountOrigin.getBalance()-amount;
    Double auxDestiny=accountDestiny.getBalance()+amount;
//actualizo los montos

        accountOrigin.setBalance(auxOrigin);
        accountDestiny.setBalance(auxDestiny);

        return new ResponseEntity<>("201 tranferencia con exito",HttpStatus.CREATED);
    }

}

