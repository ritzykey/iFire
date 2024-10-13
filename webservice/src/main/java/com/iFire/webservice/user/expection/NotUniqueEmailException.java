package com.ifire.webservice.user.expection;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ifire.webservice.shared.Messages;

public class NotUniqueEmailException extends RuntimeException {
    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("iFire.error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("email", Messages.getMessageForLocale("iFire.constraints.email.notunique",
                LocaleContextHolder.getLocale()));
    }
}
