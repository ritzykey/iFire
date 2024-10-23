package com.ifire.webservice.auth.token;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ifire.webservice.auth.dto.Credentials;
import com.ifire.webservice.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
@Primary
public class JwtTokenService implements TokenService {

    SecretKey key = Keys.hmacShaKeyFor("secret-must-be-at-least-32-chars".getBytes());

    @Override
    public Token createToken(User user, Credentials credentials) {
        String token = Jwts.builder().subject(user.getId()).signWith(key).compact();
        return new Token("Bearer", token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if (authorizationHeader == null)
            return null;
        var token = authorizationHeader.split("Bearer ")[1];
        try {
            JwtParser parser = Jwts.parser().verifyWith(key).build();
            Jws<Claims> claims = parser.parseSignedClaims(token);
            String userId = claims.getPayload().getSubject();
            User user = new User();
            user.setId(userId);
            return user;

        } catch (JwtException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
