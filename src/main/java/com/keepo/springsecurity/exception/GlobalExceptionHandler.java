package com.keepo.springsecurity.exception;

import com.keepo.springsecurity.data.resp.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseWrapper<ExceptionResponse> handleAccessDeniedException(AccessDeniedException ex) {
        return buildExceptionResponse("Access denied", HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseWrapper<ExceptionResponse> handleAuthenticationException(AuthenticationException ex) {
        return buildExceptionResponse("Authentication failed", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseWrapper<ExceptionResponse> handleBadCredentialsException(BadCredentialsException ex) {
        return buildExceptionResponse("Invalid credentials", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(AccountLockedException.class)
    public ResponseWrapper<ExceptionResponse> handleAccountLockedException(AccountLockedException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.LOCKED.value());
    }

    @ExceptionHandler(value = { ResourceAlreadyExistsException.class })
    public ResponseWrapper<ExceptionResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.ALREADY_REPORTED.value());
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseWrapper<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = { InvalidTokenException.class })
    public ResponseWrapper<ExceptionResponse> handleInvalidTokenException(InvalidTokenException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = { AccountAlreadyVerifiedException.class })
    public ResponseWrapper<ExceptionResponse> handleAccountAlreadyVerifiedException(AccountAlreadyVerifiedException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = { InvalidOtpException.class })
    public ResponseWrapper<ExceptionResponse> handleInvalidOtpException(InvalidOtpException ex) {
        return buildExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(AccountNotActiveException.class)
    public ResponseWrapper<ExceptionResponse> handleAccountNotActiveException(AccountNotActiveException ex) {
        log.warn("Account not active attempt: {}", ex.getMessage());
        return buildExceptionResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseWrapper<ExceptionResponse> handleUnauthorizedException(UnauthorizedException ex) {
        log.warn("Unauthorized access attempt: {}", ex.getMessage());
        return buildExceptionResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(EmailSendException.class)
    public ResponseWrapper<ExceptionResponse> handleEmailSendException(EmailSendException ex) {
        log.error("Email sending failed", ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "Failed to send email. Please try again later"
        );

        return ResponseWrapper.failed(
                exceptionResponse,
                HttpStatus.SERVICE_UNAVAILABLE.value()
        );
    }

    @ExceptionHandler(StorageException.class)
    public ResponseWrapper<ExceptionResponse> handleStorageException(StorageException ex) {
        log.error("Storage operation failed", ex);
        return buildExceptionResponse(
            ex.getMessage(), 
            ex.getStatus() != null ? ex.getStatus().value() : HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
    }

    private ResponseWrapper<ExceptionResponse> buildExceptionResponse(String message, int statusCode) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(message);
        return ResponseWrapper.failed(exceptionResponse, statusCode);
    }
}