package com.example.librarymanagermentservice.exception;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;

/**
 * Information about unauthorized exception.
 */
public class UnauthorizedException extends BaseApiException {
    public UnauthorizedException(String message) {
        super(StatusCodeConstant.UNAUTHORIZED, StatusCodeStringConstant.UNAUTHORIZED, message);
    }
}

