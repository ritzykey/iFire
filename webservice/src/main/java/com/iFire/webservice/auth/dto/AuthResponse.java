package com.ifire.webservice.auth.dto;

import com.ifire.webservice.auth.token.Token;
import com.ifire.webservice.user.dto.UserDTO;

import lombok.Data;

@Data
public class AuthResponse {
    

    UserDTO user;

    Token token;

}
