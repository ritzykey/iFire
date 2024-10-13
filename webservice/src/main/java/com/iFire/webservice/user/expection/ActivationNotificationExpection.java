package com.ifire.webservice.user.expection;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ifire.webservice.shared.Messages;

public class ActivationNotificationExpection extends RuntimeException {
    public ActivationNotificationExpection() {
        super(Messages.getMessageForLocale("iFire.create.user.email.failure", LocaleContextHolder.getLocale()));
    }

}
