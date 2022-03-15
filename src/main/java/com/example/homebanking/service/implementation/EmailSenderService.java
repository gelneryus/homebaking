package com.example.homebanking.service.implementation;

import com.example.homebanking.models.Client;
import com.example.homebanking.repositories.ClientRepository;
import com.example.homebanking.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    ClientRepository clientRepository;

    public void sendSimpleEmailTo(String CorreoDelDestinatario,
                                 String MensajeAenviar,
                                 String tema) {

        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setFrom("gelneryus20@gmail.com");
        mensaje.setTo(CorreoDelDestinatario);
        mensaje.setText(MensajeAenviar);
        mensaje.setSubject(tema);

        mailSender.send(mensaje);
        System.out.println("Email enviado ...");

    Client client=clientRepository.findByEmail(CorreoDelDestinatario);

    }

}


