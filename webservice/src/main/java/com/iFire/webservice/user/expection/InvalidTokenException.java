package com.ifire.webservice.user.expection;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ifire.webservice.shared.Messages;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super(Messages.getMessageForLocale("iFire.activate.user.invalid.token", LocaleContextHolder.getLocale()));

    }
}
