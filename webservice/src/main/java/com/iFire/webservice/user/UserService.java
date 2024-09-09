package com.iFire.webservice.user;

import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iFire.webservice.user.expection.ActivationNotificationExpection;
import com.iFire.webservice.user.expection.NotUniqueEmailException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(rollbackOn = MailException.class)
    public void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            sendActivationEmail(user);

        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        } catch (MailException exception){
            throw new ActivationNotificationExpection();
        }
    }

    private void sendActivationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@my-app.com");
        message.setTo(user.getEmail());
        message.setSubject("Account Activation");
        message.setText("http://localhost:5173/activation/" + user.getActivationToken());
        getjJavaMailSender().send(message);
    }

    public JavaMailSender getjJavaMailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost("smtp.ethereal.email");
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername("vena.rice@ethereal.email");
        mailSenderImpl.setPassword("kXjkqqECcRHbfgzKhA-");

        Properties properties = mailSenderImpl.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");

        return mailSenderImpl;
    }
}