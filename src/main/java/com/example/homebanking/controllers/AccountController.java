package com.example.homebanking.controllers;


import com.example.homebanking.DTOS.AccountDTO;
import com.example.homebanking.models.Account;
import com.example.homebanking.models.Client;
import com.example.homebanking.models.TypeAccounts;
import com.example.homebanking.repositories.AccountRepository;
import com.example.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getaccountDTO() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    ;

    @RequestMapping("/accounts/{id}")
    public AccountDTO getaccountDTO(@PathVariable Long id) {
        AccountDTO accountId = new AccountDTO(accountRepository.findById(id).orElse(null));
        return accountId;
    }

    int min = 00000000;
    int max = 99999999;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getStringRandomNumber() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    @RequestMapping(path = "clients/current/accounts", method = RequestMethod.POST)

    public ResponseEntity<Object> registerAccount(Authentication authentication, @RequestParam String typeAccounts) {
        TypeAccounts typeAccounts1 = TypeAccounts.valueOf(typeAccounts);
        Client client = clientRepository.findByEmail(authentication.getName());

        if (client.getAccount().size() >= 3) {
            return new ResponseEntity<>("No puede crear mas cuentas", HttpStatus.FORBIDDEN);
        }

        String number = getStringRandomNumber();

        Account account = new Account("VIN" + number, LocalDateTime.now(), 0, typeAccounts1, client, true);

        accountRepository.save(account);
        clientRepository.save(client);


        return new ResponseEntity<>("Nueva cuenta creada", HttpStatus.CREATED);
    }

    @PatchMapping("/clients/current/accounts/delete/{id}")
    public ResponseEntity<Object> smartDelete(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElse(null);


        if (account.getBalance() > 0) {
            return new ResponseEntity<>("No puede eliminar la cuenta con saldo", HttpStatus.FORBIDDEN);
        }

            account.setActive(false);
            accountRepository.save(account);

            return new ResponseEntity<>("Cuenta borrada", HttpStatus.CREATED);
    }
    @PatchMapping("/clients/current/accounts/change/{id}")
    public ResponseEntity<Object>cambio(@PathVariable Long id){
        Account account1 = accountRepository.findById(id).orElse(null);

        if (account1.getTypeAccounts() == TypeAccounts.CORRIENTE) {
            account1.setTypeAccounts(TypeAccounts.AHORRO);
        } else {
            account1.setTypeAccounts(TypeAccounts.CORRIENTE);
        }

        accountRepository.save(account1);

        return new ResponseEntity<>("Estado de la cuenta modificada", HttpStatus.CREATED);
    }

}














