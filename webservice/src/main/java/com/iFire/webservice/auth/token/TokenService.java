package com.ifire.webservice.auth.token;

import com.ifire.webservice.auth.dto.Credentials;
import com.ifire.webservice.user.User;

public interface TokenService {

    public Token createToken(User user, Credentials credentials);

    public User verifyToken(String authorizationHeader);
    
}
