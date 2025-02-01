package com.keepo.springsecurity.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class StorageException extends RuntimeException {
    
    private final String message;
    private final HttpStatus status;

    public StorageException(String message) {
        super(message);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public StorageException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public StorageException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.message = message;
        this.status = status;
    }
}
