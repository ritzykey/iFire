package com.ifire.webservice.auth.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ifire.webservice.shared.Messages;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super(Messages.getMessageForLocale("iFire.auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }
}
