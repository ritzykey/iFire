package com.ifire.webservice.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifire.webservice.email.EmailService;
import com.ifire.webservice.user.dto.UserDTO;
import com.ifire.webservice.user.expection.ActivationNotificationExpection;
import com.ifire.webservice.user.expection.InvalidTokenException;
import com.ifire.webservice.user.expection.NotUniqueEmailException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional(rollbackOn = MailException.class)
    public void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());

        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        } catch (MailException exception) {
            throw new ActivationNotificationExpection();
        }
    }

    public void activateUser(String token) {
        User user = userRepository.findByActivationToken(token);

        if (user == null) {
            throw new InvalidTokenException();
        }

        user.setActive(true);
        user.setActivationToken(null);
        userRepository.save(user);
    }

    public Page<UserDTO> getAllUsers(Pageable pageable, User loggedInUser) {

        if (loggedInUser == null) {
            return userRepository.findAll(pageable).map(UserDTO::new);
        }

        return userRepository.findByIdNot(loggedInUser.getId(), pageable).map(UserDTO::new);

    }

    public UserDTO getIdUser(Long id) {

        return new UserDTO(userRepository.findById(id).get());
    }

    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

}