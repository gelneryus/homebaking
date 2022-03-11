package com.example.homebanking.controllers;


import com.example.homebanking.DTOS.*;
import com.example.homebanking.models.*;
import com.example.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientLoansRepository clientLoansRepository;

    @RequestMapping(path = "/loans", method = RequestMethod.GET)
    public List<LoanDTO> getLoanApplicationDTO() {

        return loanRepository.findAll().stream().map(loan->new LoanDTO(loan)).collect(Collectors.toList());
    }


    @Transactional
    @RequestMapping(value="/loans",method = RequestMethod.POST)
    public ResponseEntity<String>newLoans(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {

        Client client = clientRepository.findByEmail(authentication.getName());
        Loan loan = loanRepository.findByName(loanApplicationDTO.getLoanTypeId());
        Account account = accountRepository.findByNumber(loanApplicationDTO.getNumberAccount());

        Account accountOrigin=accountRepository.findByNumber(account.getNumber());
        Account accountDestiny=accountRepository.findByNumber(account.getNumber());

        if (loanApplicationDTO == null) {
            return new ResponseEntity<>("NO SE ENCUENTRA LOS DATOS", HttpStatus.FORBIDDEN);
        }

            //verifico que los datos no esten vacios
        if (loanApplicationDTO.getAmount()== 0 || loanApplicationDTO.getNumberAccount().isEmpty() || loanApplicationDTO.getPayments()==0 || Objects.equals(loanApplicationDTO.getLoanTypeId(), "")) {
            return new ResponseEntity<>("LOS DATOS NO SON CORRECTOS", HttpStatus.FORBIDDEN);
        }



     if(account== null){
         return new ResponseEntity<>("LA CUENTA A SOLICITAR NO EXISTE ",HttpStatus.FORBIDDEN);

     }

        if (!client.getAccount().contains(account)){
            return new ResponseEntity<>("NO SE PUDO AUTENTICAR LAS CUENTAS",HttpStatus.FORBIDDEN);

        }
            if (loan==null){
                return new ResponseEntity<>("NO SE PUDO VERIFICAR EL PRESTAMO",HttpStatus.FORBIDDEN);
            }

            if (loanApplicationDTO.getAmount()>loan.getMaxAmount() || loanApplicationDTO.getAmount() <=0){
                    return new ResponseEntity<>("EXCEDE EN EL MONTO MAXIMO DISPONIBLE O EL MONTO ES NEGATIVO ",HttpStatus.FORBIDDEN);
                        }

            if (!loan.getPayments().contains(loanApplicationDTO.getPayments())){
                    return new ResponseEntity<>("403 ERROR ",HttpStatus.FORBIDDEN);
                }

                double loanInterest=(loanApplicationDTO.getAmount()*0.20)+loanApplicationDTO.getAmount();
                double loanPayments=Math.floor(loanInterest/loanApplicationDTO.getPayments());


        ClientLoans clientLoans=new ClientLoans(loanApplicationDTO.getAmount(),loanApplicationDTO.getPayments(),client,loan);
        Transactions transactionsLoanCredit=new Transactions(TransactionsType.CREDITO, loanApplicationDTO.getAmount(),loan.getName(),LocalDateTime.now(),account);

        transactionRepository.save(transactionsLoanCredit);
        clientLoansRepository.save(clientLoans);

//descuento de las cuentas

        Double auxOrigin= loanApplicationDTO.getAmount()- account.getBalance();
        Double auxDestiny= loanApplicationDTO.getAmount()+ account.getBalance();

//actualizo los montos

        accountOrigin.setBalance(auxOrigin);
        accountDestiny.setBalance(auxDestiny);

          return new ResponseEntity<>("SE APLICADO EL PRESTAMO",HttpStatus.CREATED);
    }
    @PostMapping("/api/admin/loan")
    public ResponseEntity<?> createLoan(Authentication authentication, @RequestBody LoanDTO loanDTO){
        Client client = clientRepository.findByEmail(authentication.getName());

        if (!client.getEmail().contains("@admin.com")){
            return new ResponseEntity<>("no esta autorizado", HttpStatus.FORBIDDEN);
        }
        if (loanDTO.getInteres() == 0 || loanDTO.getAmount() == 0 || loanDTO.getName().isEmpty() || loanDTO.getPayments().isEmpty()){
            return new ResponseEntity<>(" espacios vacios ", HttpStatus.FORBIDDEN);
        }

        loanRepository.save(new Loan(loanDTO.getName(), loanDTO.getAmount(), loanDTO.getPayments(),loanDTO.getInteres()));
        return new ResponseEntity<>("nuevo prestamo adherido ", HttpStatus.CREATED);
    }

}
