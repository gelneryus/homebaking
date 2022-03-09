package com.example.homebanking.controllers;


import com.example.homebanking.DTOS.ClientDTO;
import com.example.homebanking.models.Account;
import com.example.homebanking.models.Client;
import com.example.homebanking.models.TypeAccounts;
import com.example.homebanking.repositories.AccountRepository;
import com.example.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})

@RequestMapping("/api")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/clients")
    public List<ClientDTO> getClientDTO() {

        return clientRepository.findAll().stream().map(client->new ClientDTO(client)).collect(Collectors.toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClientDTO(@PathVariable Long id) {
        ClientDTO clientId = new ClientDTO(clientRepository.findById(id).orElse(null));
        return clientId;
    }

    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String firstName, @RequestParam String lastName,
                                           @RequestParam String email, @RequestParam String password ,@RequestParam String typeAccounts) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Rellene por favor los espacios vacios", HttpStatus.FORBIDDEN);

        }

        if (clientRepository.findByEmail(email) != null ){
     return new ResponseEntity<>("El dato introducido ya esta en uso",HttpStatus.FORBIDDEN);
        }

        Client client= new Client(firstName,lastName,email,passwordEncoder.encode(password));
        clientRepository.save(client);

        String  number=getStringRandomClient() ;
        TypeAccounts typeAccounts1=TypeAccounts.valueOf(typeAccounts);

        Account account= new Account("VIN"+number, LocalDateTime.now(),0,typeAccounts1,client,true);
        accountRepository.save(account);

        return new ResponseEntity<> ("Se ha registrado con exito",HttpStatus.CREATED);
    }

    @RequestMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication){
        ClientDTO  clientDTO1= new ClientDTO(clientRepository.findByEmail(authentication.getName()));
        return clientDTO1;
    }
    int min =00000000;
    int max =99999999;
    public int getRandomNumber(int min,int max){return (int) ((Math.random() * (max - min)) + min);}
    public String getStringRandomClient() {
        int randomNumber = getRandomNumber(min, max);
        return String.valueOf(randomNumber);
    }

    }
