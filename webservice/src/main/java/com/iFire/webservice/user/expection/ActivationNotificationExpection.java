package com.iFire.webservice.user.expection;

import org.springframework.context.i18n.LocaleContextHolder;

import com.iFire.webservice.shared.Messages;

public class ActivationNotificationExpection extends RuntimeException {
    public ActivationNotificationExpection() {
        super(Messages.getMessageForLocale("iFire.create.user.email.failure", LocaleContextHolder.getLocale()));
    }

}
