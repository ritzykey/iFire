package com.iFire.webservice.user.dto;

import com.iFire.webservice.user.User;
import com.iFire.webservice.user.validation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
        @NotBlank(message = "{iFire.constraints.Username.NotBlank}") @Size(min = 4, max = 255) String username,

        @NotBlank @Email @UniqueEmail(message = "{iFire.constraints.email.notunique}") String email,

        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "{iFire.constraints.password.pattern}") String password) {
    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        return user;
    }

}
