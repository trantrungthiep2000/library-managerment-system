package com.example.librarymanagermentservice.exception;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;

/**
 * Information about forbidden exception.
 */
public class ForbiddenException extends BaseApiException {
    public ForbiddenException(String message) {
        super(StatusCodeConstant.FORBIDDEN, StatusCodeStringConstant.FORBIDDEN, message);
    }
}
