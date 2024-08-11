package com.iFire.webservice.user.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.iFire.webservice.user.User;
import com.iFire.webservice.user.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * UniqueEmailValidator
 */
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        User inDB = userRepository.findByEmail(value);

        return inDB == null;

    }

}