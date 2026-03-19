package com.example.librarymanagermentservice.exception;

import com.example.librarymanagermentservice.common.StatusCodeConstant;
import com.example.librarymanagermentservice.common.StatusCodeStringConstant;

/**
 * Information about internal server exception.
 */
public class InternalServerException extends BaseApiException {
    public InternalServerException(String message) {
        super(StatusCodeConstant.INTERNAL_SERVER_ERROR, StatusCodeStringConstant.INTERNAL_SERVER_ERROR, message);
    }
}
