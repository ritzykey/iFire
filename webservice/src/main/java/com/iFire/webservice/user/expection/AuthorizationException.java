package com.ifire.webservice.user.expection;

/**
 * AuthorizationException
 */
public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("Forbidden");
    }
}