package com.iFire.webservice.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.iFire.webservice.configuration.iFireProperties;

import jakarta.annotation.PostConstruct;

@Service
public class EmailService {

    JavaMailSenderImpl mailSenderImpl;

    @Autowired
    iFireProperties iFireProperties;

    @PostConstruct
    public void initialize() {
        System.out.println("----------");
        System.out.println(iFireProperties.getEmail().clientHost());
        System.out.println("----------");

        this.mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(iFireProperties.getEmail().host());
        mailSenderImpl.setPort(iFireProperties.getEmail().port());
        mailSenderImpl.setUsername(iFireProperties.getEmail().username());
        mailSenderImpl.setPassword(iFireProperties.getEmail().password());

        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");

    }

    public void sendActivationEmail(String email, String activationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(iFireProperties.getEmail().from());
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText(iFireProperties.getEmail().clientHost() + "/activation/" + activationToken);
        this.mailSenderImpl.send(message);
    }

}
