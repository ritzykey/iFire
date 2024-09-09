package com.iFire.webservice.email;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    JavaMailSenderImpl mailSenderImpl;

    public EmailService(){
        this.initialize();
    }

    public void initialize() {
        this.mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost("smtp.ethereal.email");
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername("vena.rice@ethereal.email");
        mailSenderImpl.setPassword("kXjkqqECcRHbfgzKhA");

        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");

    }

    public void sendActivationEmail(String email, String activationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@my-app.com");
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText("http://localhost:5173/activation/" + activationToken);
        this.mailSenderImpl.send(message);
    }

}
