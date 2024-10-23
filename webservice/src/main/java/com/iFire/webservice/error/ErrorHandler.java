package com.ifire.webservice.error;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ifire.webservice.auth.exception.AuthenticationException;
import com.ifire.webservice.shared.Messages;
import com.ifire.webservice.user.expection.ActivationNotificationException;
import com.ifire.webservice.user.expection.InvalidTokenException;
import com.ifire.webservice.user.expection.NotUniqueEmailException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            NoSuchElementException.class,
            AuthenticationException.class,
            InvalidTokenException.class,
            ActivationNotificationException.class,
            NotUniqueEmailException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiError> handleException(HttpServletRequest request, Exception exception) {
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());

        apiError.setMessage(exception.getMessage());

        if (exception instanceof MethodArgumentNotValidException) {
            String message = Messages.getMessageForLocale("iFire.error.validation", LocaleContextHolder.getLocale());
            apiError.setMessage(message);
            apiError.setStatus(400);
            Map<String, String> validationErrors = ((MethodArgumentNotValidException) exception).getBindingResult()
                    .getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                            (existing, replacing) -> existing));
            validationErrors.put("exceptionClass", exception.toString());
            apiError.setValidationErrors(validationErrors);
            return ResponseEntity.status(400).body(apiError);
        }

        if (exception instanceof NotUniqueEmailException) {
            apiError.setStatus(400);
            apiError.setValidationErrors(((NotUniqueEmailException) exception).getValidationErrors());
            return ResponseEntity.status(400).body(apiError);
        }

        if (exception instanceof ActivationNotificationException) {
            apiError.setStatus(502);
            return ResponseEntity.status(502).body(apiError);
        }

        if (exception instanceof InvalidTokenException) {
            apiError.setStatus(400);
            return ResponseEntity.status(400).body(apiError);
        }

        if (exception instanceof NoSuchElementException) {
            String message = Messages.getMessageForLocale("iFire.error.noSuchElement", LocaleContextHolder.getLocale());
            apiError.setMessage(message);
            apiError.setStatus(404);
            return ResponseEntity.status(404).body(apiError);
        }

        if (exception instanceof AuthenticationException) {
            apiError.setStatus(401);
            return ResponseEntity.status(401).body(apiError);
        }

        return ResponseEntity.badRequest().body(apiError);

    }

    // @ExceptionHandler(AuthenticationException.class)
    // ResponseEntity<?> handleAuthenticationException(HttpServletRequest hRequest,
    // AuthenticationException exception) {
    // ApiError error = new ApiError();
    // error.setPath(hRequest.getRequestURI());
    // error.setStatus(401);
    // error.setMessage(exception.getMessage());

    // return ResponseEntity.status(401).body(error);
    // }

    // @ExceptionHandler(NotUniqueEmailException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // ApiError handleNotUniqueEmailEx(NotUniqueEmailException exception) {
    // ApiError apiError = new ApiError();
    // apiError.setPath("api/v1/users");
    // apiError.setMessage(exception.getMessage());
    // apiError.setStatus(400);
    // apiError.setValidationErrors(exception.getValidationErrors());
    // return apiError;
    // }

    // @ExceptionHandler(ActivationNotificationExpection.class)
    // @ResponseStatus(HttpStatus.BAD_GATEWAY)
    // ApiError handleActivationNotificationEx(ActivationNotificationExpection
    // exception) {
    // ApiError apiError = new ApiError();
    // apiError.setPath("api/v1/users");
    // apiError.setMessage(exception.getMessage());
    // apiError.setStatus(502);
    // return apiError;
    // }

    // @ExceptionHandler(InvalidTokenException.class)
    // @ResponseStatus(HttpStatus.BAD_GATEWAY)
    // ResponseEntity<ApiError> handleInvalidTokenEx(InvalidTokenException
    // exception) {
    // ApiError apiError = new ApiError();
    // apiError.setPath("api/v1/users");
    // apiError.setMessage(exception.getMessage());
    // apiError.setStatus(400);
    // return ResponseEntity.status(400).body(apiError);
    // }

    // @ExceptionHandler(NoSuchElementException.class)
    // @ResponseStatus(HttpStatus.BAD_GATEWAY)
    // ResponseEntity<ApiError> handleNoSuchElementEx(HttpServletRequest request,
    // NoSuchElementException exception) {
    // ApiError apiError = new ApiError();
    // apiError.setPath(request.getRequestURI());
    // String message = Messages.getMessageForLocale("iFire.error.noSuchElement",
    // LocaleContextHolder.getLocale());
    // apiError.setMessage(message);
    // apiError.setStatus(404);
    // return ResponseEntity.status(404).body(apiError);
    // }

}
