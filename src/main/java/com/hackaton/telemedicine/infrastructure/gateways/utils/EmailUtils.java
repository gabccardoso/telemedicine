package com.hackaton.telemedicine.infrastructure.gateways.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Properties;

@Service
public class EmailUtils {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private int port;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String auth;

    @Value("${mail.smtp.starttls.enable}")
    private String starttls;

    public void enviarEmailCompartilhamento(String emailDestinatario, String protocolo, String senha, LocalDateTime dataExpiracao) {

    Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestinatario));
        message.setSubject("Compartilhamento de Documento do Prontuário");
        message.setText("Você recebeu um documento do prontuário.\n\n" +
                "Protocolo: " + protocolo + "\n" +
                "Senha: " + senha + "\n" +
                "Data de Expiração: " + dataExpiracao);

        Transport.send(message);
    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    }
}
