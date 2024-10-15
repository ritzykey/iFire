package com.ifire.webservice.auth.token;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifire.webservice.auth.dto.Credentials;
import com.ifire.webservice.user.User;
import com.ifire.webservice.user.UserService;

/**
 * BasicAuthTokenService
 */
@Service
public class BasicAuthTokenService implements TokenService {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Token createToken(User user, Credentials credentials) {

        String emailColonPassword = credentials.email() + ":" + credentials.password();

        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());

        return new Token("Basic", token);

    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if (authorizationHeader == null)
            return null;
        var base64Encoded = authorizationHeader.split(" ")[1];
        var decoded = new String(Base64.getDecoder().decode(base64Encoded));
        var credentials = decoded.split(":");
        var email = credentials[0];
        var password = credentials[1];
        User inDB = userService.findByEmail(email);
        if (inDB == null)
            return null;
        if (!passwordEncoder.matches(password, inDB.getPassword())) return null;
        
        return inDB;
    }

}