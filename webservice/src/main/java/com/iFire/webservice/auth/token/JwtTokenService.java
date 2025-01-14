package com.ifire.webservice.auth.token;

import javax.crypto.SecretKey;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifire.webservice.auth.dto.Credentials;
import com.ifire.webservice.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
@ConditionalOnProperty(name = "iFire.token-type", havingValue = "jwt")
public class JwtTokenService implements TokenService {

    SecretKey key = Keys.hmacShaKeyFor("secret-must-be-at-least-32-chars".getBytes());

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Token createToken(User user, Credentials credentials) {
        TokenSubject tokenSubject = new TokenSubject(user.getId(), user.isActive());
        try {
            String subject = mapper.writeValueAsString(tokenSubject);
            String token = Jwts.builder().subject(subject).signWith(key).compact();
            return new Token("Bearer", token);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if (authorizationHeader == null)
            return null;
        var token = authorizationHeader.split(" ")[1];
        try {
            JwtParser parser = Jwts.parser().verifyWith(key).build();
            Jws<Claims> claims = parser.parseSignedClaims(token);
            var subject = claims.getPayload().getSubject();
            try {
                var tokenSuvject = mapper.readValue(subject, TokenSubject.class);
                User user = new User();
                user.setId(tokenSuvject.id());
                user.setActive(tokenSuvject.active());
                return user;
            } catch (JwtException | JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
            return null;

        } catch (JwtException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public static record TokenSubject(String id, boolean active) {
    }

}
