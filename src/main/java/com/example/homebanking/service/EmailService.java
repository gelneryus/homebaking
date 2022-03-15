package com.example.homebanking.service;

public interface EmailService {
    void sendSimpleEmailTo(String CorreoDelDestinatario,
                           String MensajeAenviar,
                           String tema);
}
