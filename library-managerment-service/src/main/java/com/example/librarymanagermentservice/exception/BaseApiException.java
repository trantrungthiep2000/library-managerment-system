package com.example.librarymanagermentservice.exception;

import lombok.Getter;

/**
 * Information about base api exception.
 */
@Getter
public abstract class BaseApiException extends RuntimeException {
    private final int status;
    private final String code;

    public BaseApiException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }
}
