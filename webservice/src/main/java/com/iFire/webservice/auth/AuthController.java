package com.ifire.webservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifire.webservice.auth.dto.AuthResponse;
import com.ifire.webservice.auth.dto.Credentials;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public AuthResponse handleAuthentication(@Valid @RequestBody Credentials creds) {
        return authService.authenticate(creds);
    }


}
