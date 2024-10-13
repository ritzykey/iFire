package com.ifire.webservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifire.webservice.shared.GenericMessage;
import com.ifire.webservice.shared.Messages;
import com.ifire.webservice.user.dto.UserCreate;
import com.ifire.webservice.user.dto.UserDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    Page<UserDTO> getAllUsers(Pageable page) {

        return userService.getAllUsers(page);
    }

    @GetMapping("/users/{id}")
    public UserDTO getMethodName(@PathVariable Long id) {
        return userService.getIdUser(id);
    }

    @PostMapping("/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("iFire.create.user.success.message",
                LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/users/{token}/activate")
    GenericMessage activateUser(@PathVariable String token) {
        userService.activateUser(token);

        String message = Messages.getMessageForLocale("iFire.activate.user.success.message",
                LocaleContextHolder.getLocale());

        return new GenericMessage(message);
    }



}