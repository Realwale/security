package com.keepo.springsecurity.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidOtpException extends AuthenticationException {
    public InvalidOtpException(String message) {
        super(message);
    }
}