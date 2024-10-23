package com.ifire.webservice.user.expection;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ifire.webservice.shared.Messages;

public class ActivationNotificationException extends RuntimeException {
    public ActivationNotificationException() {
        super(Messages.getMessageForLocale("iFire.create.user.email.failure", LocaleContextHolder.getLocale()));
    }

}
