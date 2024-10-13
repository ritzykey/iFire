package com.ifire.webservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifire.webservice.auth.dto.AuthResponse;
import com.ifire.webservice.auth.dto.Credentials;
import com.ifire.webservice.auth.exception.AuthenticationException;
import com.ifire.webservice.auth.token.Token;
import com.ifire.webservice.auth.token.TokenService;
import com.ifire.webservice.user.User;
import com.ifire.webservice.user.UserService;
import com.ifire.webservice.user.dto.UserDTO;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    TokenService tokenService;

    public AuthResponse authenticate(Credentials creds) {

        User inDB = userService.findByEmail(creds.email());

        if (inDB == null)
            throw new AuthenticationException();

        if (!passwordEncoder.matches(creds.password(), inDB.getPassword()))
            throw new AuthenticationException();

        Token token = tokenService.createToken(inDB, creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));

        return authResponse;

    }

}
