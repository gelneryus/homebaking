package com.example.homebanking;


import com.example.homebanking.models.*;
import com.example.homebanking.repositories.*;
import com.example.homebanking.service.implementation.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.example.homebanking.models.TransactionsType.CREDITO;
import static com.example.homebanking.models.TransactionsType.DEBITO;


@SpringBootApplication
public class HomeBankingApplication {

@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private EmailSenderService senderService;

	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail(){

		/*senderService.sendSimpleEmailTo("gelneryus20@gmail.com",
				"usted ha sido registrado con éxito y el mail del usuario",
				"HOME BANKING"); */
	}


	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository repository,
									  AccountRepository accountRepository,
									  TransactionRepository transactionRepository,
									  LoanRepository loanRepository,
									  ClientLoansRepository clientLoansRepository,
									  CardRepository cardRepository    ) {
		return (args) -> {
			// save a couple of customers2
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("123"));
			repository.save(client1);

			//manipular tiempo de cuenta
			LocalDateTime creationDate = LocalDateTime.now();
			LocalDateTime tomorrow = creationDate.plusDays(1);
			LocalDateTime fromDate = creationDate.minusDays(2);
			LocalDateTime truDate= creationDate.plusYears(5);

			Account account1 = new Account("VIN0001", creationDate, 5000,TypeAccounts.AHORRO,client1,true);
			Account account2 = new Account("VIN0002", tomorrow, 7000,TypeAccounts.AHORRO ,client1,true);
			accountRepository.save(account1);
			accountRepository.save(account2);

			Transactions transactions1= new Transactions(DEBITO,200.0,"compra", LocalDateTime.now(),account1);
			Transactions transactions2= new Transactions(CREDITO,220.0,"venta",tomorrow,account2);
			transactionRepository.save(transactions1);
			transactionRepository.save(transactions2);
			Transactions transactions3= new Transactions(DEBITO,300.2,"compra",LocalDateTime.now(),account2);
			Transactions transactions4= new Transactions(CREDITO,500.0,"venta",LocalDateTime.now(),account1 );
			transactionRepository.save(transactions3);
			transactionRepository.save(transactions4);

			List<Integer> payments =Arrays.asList (60,40,24,12);
			List<Integer> payments2 = Arrays.asList(48,24,12);
			List<Integer> payments3 = Arrays.asList(80,60,48,24,12);
			Loan loan1= new Loan("hipotecario",500000.0,payments);
			Loan loan2= new Loan("personal", 200000.0,payments2);
			Loan loan3= new Loan("automotriz", 100000.0,payments3);
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoans clientLoans1= new ClientLoans(400000.0,60,client1,loan1);
			ClientLoans clientLoans2= new ClientLoans(50000.0,12,client1,loan2);
			clientLoansRepository.save(clientLoans1);
			clientLoansRepository.save(clientLoans2);

			Card card1= new Card(client1.getFirstName()+" "+ client1.getLastName(),DEBITO,CardColor.GOLD,"3456-5573-5578-5678",344,fromDate,truDate,client1,true);
			Card card2= new Card(client1.getFirstName()+" "+client1.getLastName(),CREDITO,CardColor.SILVER,"4563-3489-8790-5678",675,fromDate,truDate,client1,true);
			cardRepository.save(card1);
			cardRepository.save(card2);

		};

	}
}
