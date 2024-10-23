package com.ifire.webservice.user;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifire.webservice.configuration.CurrentUser;
import com.ifire.webservice.email.EmailService;
import com.ifire.webservice.file.FileService;
import com.ifire.webservice.user.dto.UserDTO;
import com.ifire.webservice.user.dto.UserUpdate;
import com.ifire.webservice.user.expection.ActivationNotificationException;
import com.ifire.webservice.user.expection.InvalidTokenException;
import com.ifire.webservice.user.expection.NotUniqueEmailException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FileService fileService;

    @Transactional(rollbackFor = MailException.class)
    public void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.save(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());

        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        } catch (MailException exception) {
            throw new ActivationNotificationException();
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

    public Page<UserDTO> getAllUsers(Pageable pageable, CurrentUser loggedInUser) {

        if (loggedInUser == null) {
            return userRepository.findAll(pageable).map(UserDTO::new);
        }

        return userRepository.findByIdNot(loggedInUser.getId(), pageable).map(UserDTO::new);

    }

    public User getIdUser(String id) {

        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public UserDTO updateUser(String id, UserUpdate userUpdate) {
        User inDB = getIdUser(id);
        inDB.setUsername(userUpdate.username());

        if (userUpdate.image() == null) {
            return new UserDTO(userRepository.save(inDB));
        }

        String fileName = fileService.saveBase64StringFile(userUpdate.image());
        fileService.deleteProfileImage(inDB.getImage());
        inDB.setImage(fileName);
        return new UserDTO(userRepository.save(inDB));
    }

}