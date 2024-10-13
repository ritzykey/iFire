package com.ifire.webservice.auth.token;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.ifire.webservice.auth.dto.Credentials;
import com.ifire.webservice.user.User;

/**
 * BasicAuthTokenService
 */
@Service
public class BasicAuthTokenService implements TokenService {

    @Override
    public Token createToken(User user, Credentials credentials) {

        String emailColonPassword = credentials.email() + ":" + credentials.password();

        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());

        return new Token("Basic", token);

    }

    @Override
    public User verifyToken(String authorizationHeader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyToken'");
    }

}